package com.example.screenerapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.screenerapp.data.local.entities.StockEntity

@Dao
interface StockDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStockList(stockEntityList: List<StockEntity>)

    @Query("SELECT * FROM stock_list WHERE LOWER(name) LIKE '%' || LOWER(:query) || '%' OR LOWER(ticker) LIKE '%' || LOWER(:query) || '%'")
    suspend fun getStockList(query: String): List<StockEntity>
}