package com.example.screenerapp.data

import com.example.screenerapp.data.local.dao.StockDao
import com.example.screenerapp.data.local.entities.StockEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockListRepository @Inject constructor (private val stockDao: StockDao) {
    suspend fun getStockList(): Flow<List<StockEntity>> {
        return stockDao.getStockList()
    }
}