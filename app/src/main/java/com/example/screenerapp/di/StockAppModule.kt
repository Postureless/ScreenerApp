package com.example.screenerapp.di

import android.app.Application
import androidx.room.Room
import com.example.screenerapp.data.StockRepositoryImpl
import com.example.screenerapp.data.local.dao.StockDao
import com.example.screenerapp.data.local.database.StockDatabase
import com.example.screenerapp.network.StockApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StockAppModule {
    @Provides
    @Singleton
    fun provideStockApi(): StockApiService {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        return Retrofit.Builder()
            .baseUrl(StockApiService.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(StockApiService::class.java)
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
    fun provideStockRepository(
        db: StockDatabase,
        api: StockApiService
    ): StockRepositoryImpl {
        return StockRepositoryImpl(db, api)
    }

}