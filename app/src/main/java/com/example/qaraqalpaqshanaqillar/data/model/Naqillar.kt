package com.example.qaraqalpaqshanaqillar.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Naqil")
data class Naqillar(
    @PrimaryKey val Id: Int,

    @ColumnInfo(name="naqil")
    val type: String,

    @ColumnInfo(name="type")
    val name: Int,

    @ColumnInfo(name="favourites")
    val favourites: Int,

    )