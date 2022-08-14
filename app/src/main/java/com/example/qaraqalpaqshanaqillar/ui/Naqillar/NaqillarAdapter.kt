package com.example.qaraqalpaqshanaqillar.ui.Naqillar

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.qaraqalpaqshanaqillar.R
import com.example.qaraqalpaqshanaqillar.data.model.Naqillar
import com.example.qaraqalpaqshanaqillar.databinding.ItemNaqillarBinding

class NaqillarAdapter : RecyclerView.Adapter<NaqillarAdapter.NaqillarViewHolder>() {

    var models: List<Naqillar> = listOf()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NaqillarViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_naqillar, parent, false)
        val binding = ItemNaqillarBinding.bind(itemView)
        return NaqillarViewHolder(binding)
    }

    override fun getItemCount() = models.size

    override fun onBindViewHolder(holder: NaqillarViewHolder, position: Int) {
        holder.populateModel(models[position])
    }

    inner class NaqillarViewHolder(private val binding: ItemNaqillarBinding) : RecyclerView.ViewHolder(binding.root) {

        fun populateModel(naqillar: Naqillar) {
            binding.tvTitle.text = naqillar.naqil
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
}

    private var onClick: (naqil: Naqillar) -> Unit = {}
    fun setOnClick(onClick: (naqil: Naqillar) -> Unit) {
        this.onClick = onClick
    }
}