package com.example.qaraqalpaqshanaqillar.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.qaraqalpaqshanaqillar.data.dao.NaqilDao
import com.example.qaraqalpaqshanaqillar.data.dao.NaqillarDao
import com.example.qaraqalpaqshanaqillar.data.model.Naqil
import com.example.qaraqalpaqshanaqillar.data.model.Naqillar

@Database(entities = [Naqil::class, Naqillar::class], version = 3)
abstract class NaqilDatabase : RoomDatabase() {
    companion object {
        private var INSTANCE: NaqilDatabase? = null
        private val LOCK = Any()

        fun getInstance(context: Context): NaqilDatabase {
            INSTANCE?.let {
                return it
            }
            synchronized(LOCK) {
                INSTANCE?.let {
                    return it
                }
                val db = Room.databaseBuilder(
                    context,
                    NaqilDatabase::class.java,
                    "NaqilMaqal.db"
                )
                    .createFromAsset("NaqilMaqal.db")
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = db
                return db
            }
        }
    }

    abstract fun dao(): NaqilDao

    abstract fun naqildao(): NaqillarDao
}