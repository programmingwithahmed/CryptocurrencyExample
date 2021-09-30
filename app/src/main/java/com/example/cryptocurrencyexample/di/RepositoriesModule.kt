package com.example.cryptocurrencyexample.di

import com.example.cryptocurrencyexample.data.remote.CoinApi
import com.example.cryptocurrencyexample.data.repository.CoinRepositoryImpl
import com.example.cryptocurrencyexample.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoriesModule {


    @Provides
    @Singleton
    fun provideCoinRepository(coinApi: CoinApi): CoinRepository =
        CoinRepositoryImpl(coinApi)


}