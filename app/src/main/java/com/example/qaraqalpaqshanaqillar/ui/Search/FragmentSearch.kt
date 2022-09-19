package com.example.qaraqalpaqshanaqillar.ui.Search

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.qaraqalpaqshanaqillar.R
import com.example.qaraqalpaqshanaqillar.data.NaqilDatabase
import com.example.qaraqalpaqshanaqillar.data.dao.NaqillarDao
import com.example.qaraqalpaqshanaqillar.data.model.Naqillar
import com.example.qaraqalpaqshanaqillar.databinding.FragmentSearchBinding
import com.example.qaraqalpaqshanaqillar.viewModel.NaqilViewModel
import com.example.qaraqalpaqshanaqillar.viewModel.NaqilarViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FragmentSearch : Fragment(R.layout.fragment_search) {
    private lateinit var binding: FragmentSearchBinding
    private val adapter = SearchAdapter()
    private lateinit var dao: NaqillarDao
    private val viewModel: NaqilarViewModel by lazy { NaqilarViewModel(dao) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dao = NaqilDatabase.getInstance(requireContext()).naqildao()
        binding = FragmentSearchBinding.bind(view)
        binding.recyclerViewNaqillar.adapter = adapter
        binding.searchView.addTextChangedListener {
            it?.let { editable ->
                val searchValue = editable.toString()
                viewModel.searchNaqillar(searchValue)
            }
        }
        adapter.setOnClick { naqil ->
            naqil.favourites = 1 - naqil.favourites
            lifecycleScope.launch {
                dao.updateNaqil(naqil)
            }
        }
        setData()
        setUpObservers()
    }

    private fun setData() {
        viewModel.getAllNaqillar()
    }

    private fun setUpObservers() {
        viewModel.naqillar.observe(viewLifecycleOwner) {
            adapter.models = it
            binding.textNoValue.isVisible = it.isEmpty()
        }
    }
}

