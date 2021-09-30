package com.example.cryptocurrencyexample.data.repository

import com.example.cryptocurrencyexample.data.remote.CoinApi
import com.example.cryptocurrencyexample.data.remote.dto.CoinDetailDto
import com.example.cryptocurrencyexample.data.remote.dto.CoinDto
import com.example.cryptocurrencyexample.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val coinApi: CoinApi
) : CoinRepository {

    override suspend fun getCoins(): List<CoinDto> {
        return coinApi.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
        return coinApi.getCoinById(coinId)
    }
}