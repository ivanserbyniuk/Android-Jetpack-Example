package com.example.ivanserbyniuk.mvvmarchitectureexample.network

import io.reactivex.android.schedulers.AndroidSchedulers

class NetApiClient {
    private val baseCoinMarketCap = "https://api.coinmarketcap.com/v1/"
    private val netApi = RetrofitServiceGenerator(baseCoinMarketCap, NetApi::class.java).netApi

    fun getCoins() = netApi.getCoins().observeOn(AndroidSchedulers.mainThread())
}