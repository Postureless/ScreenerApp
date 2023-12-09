package com.example.screenerapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.screenerapp.data.local.dao.StockDao
import com.example.screenerapp.data.mapper.toStockList
import com.example.screenerapp.domain.model.Stock
import com.example.screenerapp.network.StockApiService
import com.example.screenerapp.network.asEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class StockListDataRepository(
    private val stockDao: StockDao,
    private val api: StockApiService
) {
    val stockList: LiveData<List<Stock>> = stockDao.getStockList().map { it.toStockList() }
    suspend fun refreshStockList() {
        withContext(Dispatchers.IO) {
            val response = api.getStocks()
            stockDao.insertStockList(response.asEntity())
        }
    }
}