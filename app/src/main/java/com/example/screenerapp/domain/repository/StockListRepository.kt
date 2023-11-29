package com.example.screenerapp.domain.repository

import com.example.screenerapp.domain.model.Stock
import com.example.screenerapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface StockListRepository {
    suspend fun getStockList(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<Stock>>>
}