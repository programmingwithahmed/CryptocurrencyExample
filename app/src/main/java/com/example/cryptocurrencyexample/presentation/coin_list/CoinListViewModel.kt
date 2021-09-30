package com.example.cryptocurrencyexample.presentation.coin_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrencyexample.common.Resource
import com.example.cryptocurrencyexample.domain.model.Coin
import com.example.cryptocurrencyexample.domain.use_case.get_coins.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase
) : ViewModel() {

    private val toggleProgressBar: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    private val showError: MutableLiveData<String> = MutableLiveData<String>()
    private val coins: MutableLiveData<List<Coin>> = MutableLiveData<List<Coin>>()

    init {
        getCoins()
    }

    private fun getCoins() {

        getCoinsUseCase().onEach { result ->

            when (result) {

                is Resource.Loading -> {
                    toggleProgressBar.value = true
                }

                is Resource.Success -> {
                    toggleProgressBar.value = false
                    coins.value = result.data!!
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
    fun getCoinsLiveData() = coins

}