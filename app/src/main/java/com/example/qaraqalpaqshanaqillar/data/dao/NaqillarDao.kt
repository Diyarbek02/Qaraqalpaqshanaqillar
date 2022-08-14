package com.example.qaraqalpaqshanaqillar.data.dao

import androidx.room.Dao
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.qaraqalpaqshanaqillar.data.model.Naqillar

@Dao
interface NaqillarDao {
    @Query("SELECT * FROM Naqil")
    fun getAllNaqillar(): List<Naqillar>

    @Query("SELECT * FROM Naqil WHERE type = :type")
    fun getSelectedNaqillar(type: Int): List<Naqillar>

    @Query("SELECT * FROM Naqil WHERE favourites = 1")
    fun getFavouritesNaqillar(): List<Naqillar>

    @Query("SELECT * FROM Naqil WHERE naqil Like :searchValue ")
    fun searchNaqillar(searchValue: String): List<Naqillar>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateNaqil(naqil: Naqillar)
}