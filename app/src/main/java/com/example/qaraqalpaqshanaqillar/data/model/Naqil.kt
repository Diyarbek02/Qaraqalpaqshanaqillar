package com.example.qaraqalpaqshanaqillar.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Categories")
data class Naqil(
    @PrimaryKey val Id: Int,
    val type: Int,
    val name: String,
    )