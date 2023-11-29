package com.example.screenerapp.data.local.entities
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "prices")
data class StockPriceEntity(
    @PrimaryKey val name: String,
    val currentPrice: Double,
    val highestPrice: Double,
    val lowestPrice: Double,
    val openPrice: Double,
    val previousClosePrice: Double,
    val timestamp: Long
)
