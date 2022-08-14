package com.example.qaraqalpaqshanaqillar.ui.Search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.example.qaraqalpaqshanaqillar.ui.Naqillar.NaqillarAdapter
import com.example.qaraqalpaqshanaqillar.R
import com.example.qaraqalpaqshanaqillar.data.NaqilDatabase
import com.example.qaraqalpaqshanaqillar.data.dao.NaqillarDao
import com.example.qaraqalpaqshanaqillar.data.model.Naqil
import com.example.qaraqalpaqshanaqillar.data.model.Naqillar
import com.example.qaraqalpaqshanaqillar.databinding.FragmentSearchBinding

class FragmentSearch : Fragment(R.layout.fragment_search) {
    private lateinit var binding: FragmentSearchBinding
    private val adapter = SearchAdapter()
    private lateinit var dao: NaqillarDao

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dao = NaqilDatabase.getInstance(requireContext()).naqildao()
        binding = FragmentSearchBinding.bind(view)
        binding.recyclerViewNaqillar.adapter = adapter
        binding.searchView.addTextChangedListener {
            it?.let { editable ->
                val searchValue = editable.toString()
                val newList = dao.searchNaqillar("%$searchValue%")
                adapter.models = newList
            }
        }
        setData()
    }

    private fun setData() {
        val data = dao.getAllNaqillar()
        adapter.models = data
    }
}