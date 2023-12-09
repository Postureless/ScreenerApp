package com.example.screenerapp.di

import android.app.Application
import androidx.room.Room
import com.example.screenerapp.data.StockListDataRepository
import com.example.screenerapp.data.local.dao.StockDao
import com.example.screenerapp.data.local.database.StockDatabase
import com.example.screenerapp.network.StockApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StockAppModule {
    @Provides
    @Singleton
    fun provideStockApi(): StockApiService {
        return Retrofit.Builder()
            .baseUrl(StockApiService.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideStockDatabase(app: Application): StockDatabase {
        return Room.databaseBuilder(app, StockDatabase::class.java, "stock.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideStockDao(database: StockDatabase): StockDao {
        return database.stockDao
    }

    @Provides
    @Singleton
    fun provideStockListDataRepository(
        stockDao: StockDao,
        api: StockApiService
    ): StockListDataRepository {
        return StockListDataRepository(stockDao, api)
    }

}