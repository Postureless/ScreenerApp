package com.example.screenerapp.data

import android.content.ContentValues.TAG
import android.util.Log
import com.example.screenerapp.data.local.database.StockDatabase
import com.example.screenerapp.data.local.entities.StockEntity
import com.example.screenerapp.data.mapper.toStockList
import com.example.screenerapp.domain.model.Stock
import com.example.screenerapp.domain.repository.StockRepository
import com.example.screenerapp.network.StockApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockRepositoryImpl @Inject constructor(
    private val db: StockDatabase,
    private val api: StockApiService
): StockRepository {
    private val dao = db.stockDao

    override suspend fun getStockList(query: String): Flow<List<Stock>> {
        return flow {
            emit(dao.getStockList("%$query").toStockList())
        }
    }

    suspend fun refreshStockList() {
        withContext(Dispatchers.IO) {
            try {
                val stockList = api.getStocks()
                Log.i(TAG, "API Response: $stockList")
                val entityList = stockList.map { stockApiModel ->
                    StockEntity(
                        name = stockApiModel.description,
                        ticker = stockApiModel.symbol,
                        mic = stockApiModel.mic
                    )
                }
                Log.e(TAG, "Stock entities: " + entityList.size.toString())
                dao.insertStockList(entityList)
            } catch (e: Exception) {
                Log.e(TAG, "Error refreshing stock list", e)
            }
        }
    }
}