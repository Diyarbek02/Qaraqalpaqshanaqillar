package com.example.qaraqalpaqshanaqillar.ui.Categories

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.qaraqalpaqshanaqillar.R
import com.example.qaraqalpaqshanaqillar.data.model.Naqil
import com.example.qaraqalpaqshanaqillar.databinding.ItemCategoriesBinding

class CategoriesAdapter : RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {

    var models: List<Naqil> = listOf()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_categories, parent, false)
        val binding = ItemCategoriesBinding.bind(itemView)
        return CategoriesViewHolder(binding)
    }

    override fun getItemCount(): Int = models.size

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.populateModel(models[position])
    }

    inner class CategoriesViewHolder(private val binding: ItemCategoriesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun populateModel(naqil: Naqil) {
            binding.tvCategories.text = naqil.name
            itemView.setOnClickListener {
                onClick.invoke(naqil)
            }
        }
    }

    var onClick: (naqil: Naqil) -> Unit = {}

    fun itemClick(onClick1: (naqil: Naqil) -> Unit) {

    }
}
