package com.example.qaraqalpaqshanaqillar.ui.Search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.qaraqalpaqshanaqillar.ui.Naqillar.NaqillarAdapter
import com.example.qaraqalpaqshanaqillar.R
import com.example.qaraqalpaqshanaqillar.data.model.Naqil
import com.example.qaraqalpaqshanaqillar.databinding.FragmentSearchBinding

class FragmentSearch: Fragment(R.layout.fragment_search) {
    private lateinit var binding: FragmentSearchBinding
    private val adapter = NaqillarAdapter()
    private val list = mutableListOf<Naqil>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_search, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSearchBinding.bind(view)

        fillList()
        adapter.models = list

        binding.apply {
            recyclerViewNaqillar.adapter = adapter
        }

    }

    private fun fillList(){
        repeat(30){ j->
            list.add(Naqil(1, 2, "naqil"))
        }
    }
}