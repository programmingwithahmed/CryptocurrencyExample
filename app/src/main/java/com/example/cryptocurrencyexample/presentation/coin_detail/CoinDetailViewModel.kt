package com.example.cryptocurrencyexample.presentation.coin_detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrencyexample.common.Constant
import com.example.cryptocurrencyexample.common.Resource
import com.example.cryptocurrencyexample.domain.model.Coin
import com.example.cryptocurrencyexample.domain.model.CoinDetail
import com.example.cryptocurrencyexample.domain.use_case.get_coin.GetCoinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val toggleProgressBar: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    private val showError: MutableLiveData<String> = MutableLiveData<String>()
    private val coin: MutableLiveData<CoinDetail> = MutableLiveData<CoinDetail>()

    init {
        savedStateHandle.get<String>(Constant.PARAM_COIN_ID)?.let { coinId ->
            getCoin(coinId)
        }
    }

    private fun getCoin(coinId: String) {

        getCoinUseCase(coinId).onEach { result ->

            when (result) {

                is Resource.Loading -> {
                    toggleProgressBar.value = true
                }

                is Resource.Success -> {
                    toggleProgressBar.value = false
                    coin.value = result.data!!
                }

                is Resource.Error -> {
                    toggleProgressBar.value = false
                    showError.value = result.message!!
                }

            }

        }.launchIn(viewModelScope)

    }

    fun getToggleProgressBar() = toggleProgressBar
    fun getShowError() = showError
    fun getCoinLiveData() = coin

}