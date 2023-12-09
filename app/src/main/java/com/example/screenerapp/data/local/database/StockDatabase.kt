package com.example.screenerapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.screenerapp.data.local.dao.StockDao
import com.example.screenerapp.data.local.entities.StockEntity

@Database(version = 1, entities = [StockEntity::class], exportSchema = false)
abstract class StockDatabase : RoomDatabase() {
    abstract val stockDao: StockDao
}