package com.example.screenerapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news")
data class NewsEntity(
    @PrimaryKey val id: Long,
    val symbol: String,
    val category: String,
    val datetime: Long,
    val headline: String,
    val image: String?,
    val related: String,
    val source: String,
    val summary: String,
    val url: String
)
