package com.example.qaraqalpaqshanaqillar.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Categories")
data class Naqil(
    @PrimaryKey val Id: Int,

    @ColumnInfo(name="type")
    val type: Int,

    @ColumnInfo(name="name")
    val name: String,
    )