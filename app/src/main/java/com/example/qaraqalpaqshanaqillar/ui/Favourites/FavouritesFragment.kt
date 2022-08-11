package com.example.qaraqalpaqshanaqillar.ui.Favourites

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.qaraqalpaqshanaqillar.ui.Categories.CategoriesText
import com.example.qaraqalpaqshanaqillar.R
import com.example.qaraqalpaqshanaqillar.databinding.FragmentFavouritesBinding

class FavouritesFragment: Fragment(R.layout.fragment_favourites) {
    private lateinit var binding: FragmentFavouritesBinding
    private val list = mutableListOf<CategoriesText>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentFavouritesBinding.bind(view)
    }

    private fun fillList() {
        repeat(40) { j ->
            list.add(CategoriesText("${j + 1}"))
        }
    }

}