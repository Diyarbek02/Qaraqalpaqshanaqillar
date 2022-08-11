package com.example.qaraqalpaqshanaqillar.ui.Favourites

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.qaraqalpaqshanaqillar.R
import com.example.qaraqalpaqshanaqillar.databinding.ItemNaqillarBinding

class FavouritesAdapter: RecyclerView.Adapter<FavouritesViewHolder>() {
    var models = listOf<NaqillarText>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouritesViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_favourites, parent, false)
        val binding = ItemNaqillarBinding.bind(itemView)
        return FavouritesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavouritesViewHolder, position: Int) {
        holder.bind(models[position])
    }

    override fun getItemCount() = models.size
}
