package com.example.cryptocurrencyexample.domain.use_case.get_coins

import com.example.cryptocurrencyexample.common.Resource
import com.example.cryptocurrencyexample.data.remote.dto.toCoin
import com.example.cryptocurrencyexample.domain.model.Coin
import com.example.cryptocurrencyexample.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val coinRepository: CoinRepository
) {

    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {

        try {
            emit(Resource.Loading<List<Coin>>())

            val coins: List<Coin> = coinRepository.getCoins().map { it.toCoin() }

            emit(Resource.Success<List<Coin>>(coins))

        }catch (e: HttpException){
            emit(Resource.Error<List<Coin>>(e.localizedMessage ?: "An unexpected error happened"))
        }catch (e: IOException){
            emit(Resource.Error<List<Coin>>(e.localizedMessage ?: "Couldn't reach server. Check your internet connection."))
        }

    }

}