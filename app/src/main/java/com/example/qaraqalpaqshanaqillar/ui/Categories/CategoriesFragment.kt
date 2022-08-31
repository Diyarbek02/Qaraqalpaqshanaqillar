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

class CategoriesFragment : Fragment(R.layout.fragment_categories) {
    private lateinit var binding: FragmentCategoriesBinding
    private val adapter = CategoriesAdapter()
    private lateinit var dao: NaqilDao

    companion object{
        const val TYPE = "type"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dao = NaqilDatabase.getInstance(requireContext()).dao()

        binding = FragmentCategoriesBinding.bind(view)

        binding.apply {

            recyclerViewCategories.adapter = adapter

            adapter.models = dao.getAllCategories()

            searchView.addTextChangedListener {
                it?.let { editable ->
                    val searchValue = editable.toString()
                    val newList = dao.searchCategories("%$searchValue%")
                    adapter.models = newList
                    binding.textNoValue.isVisible = newList.isEmpty()
                }
            }
        }

        adapter.itemClick {
            val bundle = Bundle()
            bundle.putInt(TYPE, it.type)
            val naqilFragment = NaqillarFragment()
            naqilFragment.arguments = bundle
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, naqilFragment)
                .addToBackStack(naqilFragment::class.java.simpleName).commit()
        }
    }
}