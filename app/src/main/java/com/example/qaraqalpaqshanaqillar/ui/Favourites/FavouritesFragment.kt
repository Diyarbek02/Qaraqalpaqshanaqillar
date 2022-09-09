package com.example.qaraqalpaqshanaqillar.ui.Favourites

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.qaraqalpaqshanaqillar.R
import com.example.qaraqalpaqshanaqillar.data.NaqilDatabase
import com.example.qaraqalpaqshanaqillar.data.dao.NaqillarDao
import com.example.qaraqalpaqshanaqillar.databinding.FragmentFavouritesBinding
import com.example.qaraqalpaqshanaqillar.ui.Naqillar.NaqillarAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class FavouritesFragment : Fragment(R.layout.fragment_favourites) {
    private lateinit var binding: FragmentFavouritesBinding
    private val adapter = NaqillarAdapter()
    private lateinit var dao: NaqillarDao

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentFavouritesBinding.bind(view)
        dao = NaqilDatabase.getInstance(requireContext()).naqildao()

        binding.apply {
            dao.getFavouritesNaqillar()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        adapter.models = it
                    },
                    {
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    }
                )
            recyclerViewFav.adapter = adapter
        }

        adapter.setOnClick { naqil ->
            naqil.favourites = 1 - naqil.favourites
            dao.updateNaqil(naqil)
        }
    }
}