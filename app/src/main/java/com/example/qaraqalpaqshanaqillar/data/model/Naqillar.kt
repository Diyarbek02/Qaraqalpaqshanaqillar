package com.example.qaraqalpaqshanaqillar.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Naqil")
data class Naqillar(

    @PrimaryKey val id: Int,
    val naqil: String,
    val type: Int,
    var favourites: Int
    )