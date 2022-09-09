package com.example.qaraqalpaqshanaqillar.data.dao

import androidx.room.Dao
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.qaraqalpaqshanaqillar.data.model.Naqillar
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

@Dao
interface NaqillarDao {
    @Query("SELECT * FROM Naqil")
    fun getAllNaqillar(): Observable<List<Naqillar>>

    @Query("SELECT * FROM Naqil WHERE type = :type")
    fun getSelectedNaqillar(type: Int): Observable<List<Naqillar>>

    @Query("SELECT * FROM Naqil WHERE favourites = 1")
    fun getFavouritesNaqillar(): Observable<List<Naqillar>>

    @Query("SELECT * FROM Naqil WHERE naqil Like :searchValue ")
    fun searchNaqillar(searchValue: String): Observable<List<Naqillar>>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateNaqil(naqil: Naqillar) : Completable

    @Update
    fun updateQuestion(naqil: Naqillar): Completable
}