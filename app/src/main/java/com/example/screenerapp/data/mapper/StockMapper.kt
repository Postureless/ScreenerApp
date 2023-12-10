package com.example.screenerapp.data.mapper

import com.example.screenerapp.data.local.entities.StockEntity
import com.example.screenerapp.domain.model.Stock

fun StockEntity.toStock(): Stock {
    return Stock(
        name = name,
        ticker = ticker,
        mic = mic
    )
}

fun List<StockEntity>.toStockList(): List<Stock> {
    return map { stockEntity ->
        Stock(
            name = stockEntity.name,
            ticker = stockEntity.ticker,
            mic = stockEntity.mic
        )
    }
}