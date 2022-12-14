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
import com.example.qaraqalpaqshanaqillar.databinding.FragmentSearchBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FragmentSearch : Fragment(R.layout.fragment_search) {
    private val adapter = SearchAdapter()
    private lateinit var dao: NaqillarDao
    private val binding by lazy { FragmentSearchBinding.inflate(layoutInflater) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dao = NaqilDatabase.getInstance(requireContext()).naqildao()
        binding.recyclerViewNaqillar.adapter = adapter
        binding.searchView.addTextChangedListener {
            it?.let { editable ->
                val searchValue = editable.toString()

                lifecycleScope.launch {
                    try {
                        val data = dao.searchNaqillar("%$searchValue%")
                        withContext(Dispatchers.Main) {
                            adapter.models = data
                            binding.textNoValue.isVisible = data.isEmpty()
                        }
                    } catch (e: Exception) {
                        Toast.makeText(requireContext(), e.localizedMessage, Toast.LENGTH_SHORT).show()
                    }
                }

            }
        }
        adapter.setOnClick { naqil ->
            naqil.favourites = 1 - naqil.favourites
            lifecycleScope.launch {
                dao.updateNaqil(naqil)
            }
        }
        setData()
    }

    private fun setData() {
        lifecycleScope.launch {
            try {
                val data = dao.getAllNaqillar()
                withContext(Dispatchers.Main) {
                    adapter.models = data
                }
            } catch (e: Exception) {
                Toast.makeText(requireContext(), e.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }
}