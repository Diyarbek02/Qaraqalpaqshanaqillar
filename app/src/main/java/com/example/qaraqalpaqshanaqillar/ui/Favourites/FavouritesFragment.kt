package com.example.qaraqalpaqshanaqillar.ui.Favourites

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.qaraqalpaqshanaqillar.R
import com.example.qaraqalpaqshanaqillar.data.NaqilDatabase
import com.example.qaraqalpaqshanaqillar.data.dao.NaqillarDao
import com.example.qaraqalpaqshanaqillar.databinding.FragmentFavouritesBinding
import com.example.qaraqalpaqshanaqillar.ui.Naqillar.NaqillarAdapter
import com.example.qaraqalpaqshanaqillar.viewModel.NaqilarViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavouritesFragment : Fragment(R.layout.fragment_favourites) {
    private lateinit var binding: FragmentFavouritesBinding
    private val adapter = NaqillarAdapter()
    private lateinit var dao: NaqillarDao
    private val viewModel: NaqilarViewModel by lazy { NaqilarViewModel(dao) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFavouritesBinding.bind(view)
        dao = NaqilDatabase.getInstance(requireContext()).naqildao()

        binding.apply {
            viewModel.getFavouritesNaqillar()
            recyclerViewFav.adapter = adapter
        }

        adapter.setOnClick { naqil ->
            naqil.favourites = 1 - naqil.favourites
            lifecycleScope.launch {
                dao.updateNaqil(naqil)
            }
        }
        setUpObservers()
    }
    private fun setUpObservers() {
        viewModel.naqillar.observe(this) {
            adapter.models = it
        }
    }
}