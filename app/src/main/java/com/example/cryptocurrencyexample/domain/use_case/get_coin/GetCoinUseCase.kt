package com.example.cryptocurrencyexample.domain.use_case.get_coin

import com.example.cryptocurrencyexample.common.Resource
import com.example.cryptocurrencyexample.data.remote.dto.toCoin
import com.example.cryptocurrencyexample.data.remote.dto.toCoinDetail
import com.example.cryptocurrencyexample.domain.model.Coin
import com.example.cryptocurrencyexample.domain.model.CoinDetail
import com.example.cryptocurrencyexample.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val coinRepository: CoinRepository
) {

    operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> = flow {

        try {
            emit(Resource.Loading<CoinDetail>())

            val coinDetail: CoinDetail = coinRepository.getCoinById(coinId).toCoinDetail()

            emit(Resource.Success<CoinDetail>(coinDetail))

        } catch (e: HttpException) {
            emit(Resource.Error<CoinDetail>(e.localizedMessage ?: "An unexpected error happened"))
        } catch (e: IOException) {
            emit(
                Resource.Error<CoinDetail>(
                    e.localizedMessage ?: "Couldn't reach server. Check your internet connection."
                )
            )
        }


    }

}