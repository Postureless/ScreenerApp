package com.example.screenerapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "stock_details")
data class StockDetailEntity(
    @PrimaryKey val name: String,
    val ticker: String,
    val finnhubIndustry: String,
    val exchange: String,
    val marketCapitalization: Int,
    val weburl: String,
    val logo: String,
    val latestPriceTimestamp: Long?
)
