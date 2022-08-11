package com.example.qaraqalpaqshanaqillar.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.qaraqalpaqshanaqillar.data.model.Naqillar

@Dao
interface NaqillarDao {
    @Query("SELECT * FROM Naqil")
    fun getAllCategories(): List<Naqillar>
}