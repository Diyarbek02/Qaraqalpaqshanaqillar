package com.example.qaraqalpaqshanaqillar.ui.Favourites

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.qaraqalpaqshanaqillar.R
import com.example.qaraqalpaqshanaqillar.data.model.Naqil
import com.example.qaraqalpaqshanaqillar.data.model.Naqillar
import com.example.qaraqalpaqshanaqillar.databinding.ItemNaqillarBinding

class FavouritesAdapter: RecyclerView.Adapter<FavouritesAdapter.FavouritesViewHolder>() {
    var models = listOf<Naqillar>()
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
        holder.populateModel(models[position])
    }

    override fun getItemCount() = models.size

    inner class FavouritesViewHolder(private val binding: ItemNaqillarBinding): RecyclerView.ViewHolder(binding.root) {
        fun populateModel(naqillar: Naqillar) {
            binding.apply {
                tvTitle.text = naqillar.naqil

                if (naqillar.favourites == 1) {
                    ivFavourite.setImageResource(R.drawable.favourite)
                }else {
                    ivFavourite.setImageResource(R.drawable.fav)
                }

                ivFavourite.setOnClickListener{
                    onClick(naqillar)
                    if (naqillar.favourites == 1) {
                        ivFavourite.setImageResource(R.drawable.favourite)
                    }else {
                        ivFavourite.setImageResource(R.drawable.fav)
                    }
                }
            }
        }
    }

    private var onClick: (naqil: Naqillar) -> Unit = {}
    fun setOnClick(onClick: (naqil: Naqillar) -> Unit) {
        this.onClick = onClick
    }
}
