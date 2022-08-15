package com.example.qaraqalpaqshanaqillar.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Naqil")
data class Naqillar(
    @PrimaryKey val id: Int,

    @ColumnInfo(name="naqil")
    val naqil: String,

    @ColumnInfo(name="type")
    val type: Int,

    @ColumnInfo(name="favourites")
    var favourites: Int

    )