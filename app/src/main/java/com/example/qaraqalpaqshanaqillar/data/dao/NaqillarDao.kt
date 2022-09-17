package com.example.qaraqalpaqshanaqillar.data.dao

import androidx.room.Dao
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.qaraqalpaqshanaqillar.data.model.Naqillar

@Dao
interface NaqillarDao {
    @Query("SELECT * FROM Naqil")
    suspend fun getAllNaqillar():  List<Naqillar>

    @Query("SELECT * FROM Naqil WHERE type = :type")
    suspend fun getSelectedNaqillar(type: Int): List<Naqillar>

    @Query("SELECT * FROM Naqil WHERE favourites = 1")
    suspend fun getFavouritesNaqillar(): List<Naqillar>

    @Query("SELECT * FROM Naqil WHERE naqil Like :searchValue ")
    suspend fun searchNaqillar(searchValue: String): List<Naqillar>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateNaqil(naqil: Naqillar)

    @Update
    fun updateQuestion(naqil: Naqillar)
}