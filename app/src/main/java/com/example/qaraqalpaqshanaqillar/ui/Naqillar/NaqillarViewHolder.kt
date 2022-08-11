package com.example.qaraqalpaqshanaqillar.ui.Naqillar

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.example.qaraqalpaqshanaqillar.data.model.Naqil
import com.example.qaraqalpaqshanaqillar.databinding.ItemNaqillarBinding

class NaqillarViewHolder(private val binding: ItemNaqillarBinding): RecyclerView.ViewHolder(binding.root) {

    fun populateModel(naqil: Naqil) {
        binding.tvTitle.text = naqil.name
        }
    }