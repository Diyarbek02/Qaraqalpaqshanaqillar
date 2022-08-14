package com.example.qaraqalpaqshanaqillar.ui.Favourites

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.qaraqalpaqshanaqillar.ui.Categories.CategoriesText
import com.example.qaraqalpaqshanaqillar.R
import com.example.qaraqalpaqshanaqillar.data.NaqilDatabase
import com.example.qaraqalpaqshanaqillar.data.dao.NaqillarDao
import com.example.qaraqalpaqshanaqillar.databinding.FragmentFavouritesBinding

class FavouritesFragment: Fragment(R.layout.fragment_favourites) {
    private lateinit var binding: FragmentFavouritesBinding
    private val adapter = FavouritesAdapter()
    private lateinit var dao: NaqillarDao

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dao = NaqilDatabase.getInstance(requireContext()).naqildao()

        binding = FragmentFavouritesBinding.bind(view)
    }

    private fun setData() {
        adapter.models = dao.getFavouritesNaqillar()
    }
}