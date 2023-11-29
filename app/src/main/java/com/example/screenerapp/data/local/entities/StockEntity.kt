package com.example.screenerapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stock_list")
data class StockEntity(
    @PrimaryKey val name: String,
    val ticker: String,
    val mic: String
)