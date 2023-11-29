package com.example.screenerapp.network

import retrofit2.http.GET
import retrofit2.http.Query


interface StockApiService {
    @GET("/stock/symbol")
    suspend fun getStocks(
        @Query("apikey") apiKey: String,
        @Query("exchange") exchange: String,
        @Query("currency") currency: String
    )

    companion object {
        const val API_KEY = "cliu679r01qsgccbkai0cliu679r01qsgccbkaig"
        const val BASE_URL = "https://finnhub.io/api/v1"
        const val exchange = "US"
        const val currency = "USD"
    }
}