package com.example.qaraqalpaqshanaqillar.ui.Naqillar

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.qaraqalpaqshanaqillar.R
import com.example.qaraqalpaqshanaqillar.data.dao.NaqillarDao
import com.example.qaraqalpaqshanaqillar.data.model.Naqillar
import com.example.qaraqalpaqshanaqillar.databinding.ItemNaqillarBinding
import kotlinx.android.synthetic.main.item_naqillar.view.*

class NaqillarAdapter : RecyclerView.Adapter<NaqillarAdapter.NaqillarViewHolder>() {

    var models: List<Naqillar> = listOf()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private var favOnClick: (naqil: Naqillar) -> Unit = {}
    fun setOnFavIconClickListener(favOnClick: (naqil : Naqillar) -> Unit) {
        this.favOnClick = favOnClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NaqillarViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_naqillar, parent, false)
        val binding = ItemNaqillarBinding.bind(itemView)
        return NaqillarViewHolder(binding)
    }

    override fun getItemCount(): Int = models.size

    override fun onBindViewHolder(holder: NaqillarViewHolder, position: Int) {
        holder.populateModel(models[position])
    }

    inner class NaqillarViewHolder(private val binding: ItemNaqillarBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun populateModel(naqillar: Naqillar) {
            binding.apply {
                tvTitle.text = naqillar.naqil

               // setFavouriteStatus(naqillar, false)

                itemView.lottie_fav.setOnClickListener {
                    favOnClick.invoke(naqillar)
                    itemView.lottie_fav.apply {
                        if (naqillar.favourites == 1) {
                            speed = 1f
                        }else {
                            speed = -1.7f
                            setMinAndMaxFrame(0, 25)
                        }
                        playAnimation()
                    }
                   //setFavouriteStatus(naqillar, true)
                }

//                if (naqillar.favourites == 1) {
//                    ivFavourite.setImageResource(R.drawable.favourtite)
//                } else {
//                    ivFavourite.setImageResource(R.drawable.fav)
//                }
//
//                ivFavourite.setOnClickListener {
//                    onClick(naqillar)
//                    if (naqillar.favourites == 1) {
//                        ivFavourite.setImageResource(R.drawable.favourtite)
//                    } else {
//                        ivFavourite.setImageResource(R.drawable.fav)
//                    }
//                }

                val isFavourite =naqillar.favourites == 1

                if (isFavourite) {
                    itemView.lottie_fav.progress = 0.44f
                }else {
                    itemView.lottie_fav.progress = 0f
                }

                itemView.lottie_fav.setOnClickListener {
                    if (!isFavourite) {
                        itemView.lottie_fav.speed = 1f
                        itemView.lottie_fav.playAnimation()
                    }else {
                        itemView.lottie_fav.speed = -1.7f
                        itemView.lottie_fav.setMinAndMaxFrame(0, 22)
                        itemView.lottie_fav.playAnimation()
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