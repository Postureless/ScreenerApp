package com.example.screenerapp.network

import com.example.screenerapp.data.local.entities.StockEntity
import com.squareup.moshi.JsonClass
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface StockApiService {
    @GET("/stock/symbol")
    suspend fun getStocks(
        @Query("apikey") apiKey: String = API_KEY,
        @Query("exchange") exchange: String = defaultExchange,
        @Query("currency") currency: String = defaultCurrency
    ): StockApiResponse

    companion object {
        const val API_KEY = "cliu679r01qsgccbkai0cliu679r01qsgccbkaig"
        const val BASE_URL = "https://finnhub.io/api/v1"
        const val defaultExchange = "US"
        const val defaultCurrency = "USD"
    }
}

@JsonClass(generateAdapter = true)
data class StockApiResponse(
    val result: List<StockApiModel>
)

@JsonClass(generateAdapter = true)
data class StockApiModel(
    val currency: String,
    val description: String,
    val displaySymbol: String,
    val figi: String,
    val mic: String,
    val symbol: String,
    val type: String
)

fun StockApiResponse.asEntity(): List<StockEntity> {
    return result.map {
        StockEntity(
            name = it.description,
            ticker = it.symbol,
            mic = it.mic
        )
    }
}