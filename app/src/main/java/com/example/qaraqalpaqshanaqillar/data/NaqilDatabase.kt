package com.example.qaraqalpaqshanaqillar.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.qaraqalpaqshanaqillar.data.dao.NaqilDao
import com.example.qaraqalpaqshanaqillar.data.model.Naqil

@Database(entities = [Naqil::class], version = 1)
abstract class NaqilDatabase : RoomDatabase() {
    companion object {
        private lateinit var INSTANCE:NaqilDatabase

        fun getInstance(context: Context): NaqilDatabase =
            Room.databaseBuilder(
                context,
                NaqilDatabase::class.java, "NaqilMaqal.db"
            )
                .createFromAsset("NaqilMaqal.db")
                .allowMainThreadQueries()
                 .build()
    }

    abstract fun dao(): NaqilDao
}