package com.example.cryptocurrencyexample.data.remote.dto


import com.example.cryptocurrencyexample.domain.model.Coin
import com.google.gson.annotations.SerializedName

data class CoinDto(
    val id: String,
    @SerializedName("is_active")
    val isActive: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
)

fun CoinDto.toCoin(): Coin =
    Coin(
        id = id,
        isActive = isActive,
        name = name,
        rank = rank,
        symbol = symbol
    )