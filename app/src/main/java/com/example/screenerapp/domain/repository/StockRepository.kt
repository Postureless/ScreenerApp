package com.example.screenerapp.domain.repository

import com.example.screenerapp.domain.model.Stock
import kotlinx.coroutines.flow.Flow

interface StockRepository {
    suspend fun getStockList(query: String): Flow<List<Stock>>
}