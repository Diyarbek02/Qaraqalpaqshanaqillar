package com.example.qaraqalpaqshanaqillar.ui.Categories

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.example.qaraqalpaqshanaqillar.R
import com.example.qaraqalpaqshanaqillar.data.NaqilDatabase
import com.example.qaraqalpaqshanaqillar.data.dao.NaqilDao
import com.example.qaraqalpaqshanaqillar.databinding.FragmentCategoriesBinding
import com.example.qaraqalpaqshanaqillar.ui.Naqillar.NaqillarFragment
import com.example.qaraqalpaqshanaqillar.viewModel.NaqilViewModel

class CategoriesFragment : Fragment(R.layout.fragment_categories) {
    private lateinit var binding: FragmentCategoriesBinding
    private val adapter = CategoriesAdapter()
    private lateinit var dao: NaqilDao
    private val viewModel: NaqilViewModel by lazy { NaqilViewModel(dao) }

    companion object {
        const val TYPE = "type"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dao = NaqilDatabase.getInstance(requireContext()).dao()

        binding = FragmentCategoriesBinding.bind(view)

        binding.apply {
            viewModel.getAllCategoroies()

            searchView.addTextChangedListener {
                it?.let { editable ->
                    val searchValue = editable.toString()

                   viewModel.searchCategories(searchValue)
                }
            }
            recyclerViewCategories.adapter = adapter
        }

        adapter.itemClick {
            val bundle = Bundle()
            bundle.putInt(TYPE, it.type)
            val naqillarFragment = NaqillarFragment()
            naqillarFragment.arguments = bundle
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.full_container, naqillarFragment)
                .addToBackStack(naqillarFragment::class.java.simpleName)
                .commit()
        }
        setUpObservers()
    }

    private fun setUpObservers() {
        viewModel.naqil.observe(viewLifecycleOwner) {
            adapter.models = it
            binding.textNoValue.isVisible = it.isEmpty()
        }
    }
}