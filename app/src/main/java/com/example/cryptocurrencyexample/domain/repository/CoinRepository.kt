package com.example.cryptocurrencyexample.domain.repository

import com.example.cryptocurrencyexample.data.remote.dto.CoinDetailDto
import com.example.cryptocurrencyexample.data.remote.dto.CoinDto

interface CoinRepository {

    suspend fun getCoins():List<CoinDto>

    suspend fun getCoinById(coinId: String): CoinDetailDto
}