package com.example.qaraqalpaqshanaqillar

import androidx.recyclerview.widget.DiffUtil
import com.example.qaraqalpaqshanaqillar.data.model.Naqil
import com.example.qaraqalpaqshanaqillar.data.model.Naqillar

class MyDiffUtil(
    private val oldList: List<Naqillar>,
    private val newList: List<Naqillar>
): DiffUtil.Callback(

) {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return  oldList[oldItemPosition].id ==newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].id != newList[newItemPosition].id -> {
                false
            }
            oldList[oldItemPosition].naqil != newList[newItemPosition].naqil -> {
                false
            }
            oldList[oldItemPosition].type != newList[newItemPosition].type -> {
                false
            }
            oldList[oldItemPosition].favourites != newList[newItemPosition].favourites -> {
                false
            }
            else -> true
        }
    }
}