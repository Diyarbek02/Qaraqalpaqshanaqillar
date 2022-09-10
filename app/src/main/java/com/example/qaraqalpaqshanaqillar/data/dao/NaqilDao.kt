package com.example.qaraqalpaqshanaqillar.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.qaraqalpaqshanaqillar.data.model.Naqil
import io.reactivex.rxjava3.core.Observable

@Dao
interface NaqilDao  {
    @Query("SELECT * FROM Categories")
    suspend fun getAllCategories(): List<Naqil>

    @Query("SELECT * FROM  Categories WHERE name LIKE :searchValue")
    suspend fun searchCategories(searchValue: String): List<Naqil>
}