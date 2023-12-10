package com.example.screenerapp.network

import com.squareup.moshi.JsonClass
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface StockApiService {
    @GET("stock/symbol")
    suspend fun getStocks(
        @Query("exchange") exchange: String = defaultExchange,
        @Query("token") apiKey: String = API_KEY,
        @Query("currency") currency: String? = defaultCurrency
    ): List<StockApiModel>

    companion object {
        const val API_KEY = "cliu679r01qsgccbkai0cliu679r01qsgccbkaig"
        const val BASE_URL = "https://finnhub.io/api/v1/"
        const val defaultExchange = "US"
        const val defaultCurrency = "USD"
    }
}

@JsonClass(generateAdapter = true)
data class StockApiModel(
    val currency: String,
    val description: String,
    val displaySymbol: String,
    val figi: String,
    val isin: String?,
    val mic: String,
    val shareClassFIGI: String?,
    val symbol: String,
    val symbol2: String?,
    val type: String?
)
