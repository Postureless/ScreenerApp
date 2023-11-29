package com.example.screenerapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.screenerapp.data.local.entities.NewsEntity
import com.example.screenerapp.data.local.entities.StockDetailEntity
import com.example.screenerapp.data.local.entities.StockEntity
import com.example.screenerapp.data.local.entities.StockPriceEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StockDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStockList(stockEntityList: List<StockEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStockDetail(stockDetail: StockDetailEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStockPrice(stockPriceEntity: StockPriceEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(newsEntity: NewsEntity)

    @Query("SELECT * FROM stock_list")
    public abstract fun getStockList(): Flow<List<StockEntity>>

    @Query("SELECT * FROM stock_list WHERE name = :name")
    public abstract fun getStockByName(name: String): Flow<StockEntity>
}