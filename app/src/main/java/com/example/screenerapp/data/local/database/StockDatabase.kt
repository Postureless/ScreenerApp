package com.example.screenerapp.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.screenerapp.data.local.dao.StockDao
import com.example.screenerapp.data.local.entities.StockEntity

@Database(version = 1, entities = [StockEntity::class])
abstract class StockDatabase : RoomDatabase() {
    abstract fun stockDao(): StockDao

    companion object {
        @Volatile
        private var Instance: StockDatabase? = null
        fun getDatabase(context: Context): StockDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, StockDatabase::class.java, "stock_list")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}