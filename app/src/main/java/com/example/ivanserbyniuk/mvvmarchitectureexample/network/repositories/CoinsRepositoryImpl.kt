package com.example.ivanserbyniuk.mvvmarchitectureexample.network.repositories

import com.example.ivanserbyniuk.mvvmarchitectureexample.network.NetApi
import com.example.ivanserbyniuk.mvvmarchitectureexample.network.RetrofitServiceGenerator
import io.reactivex.android.schedulers.AndroidSchedulers

class CoinsRepositoryNetwork : CoinsRepository {
    private val baseCoinMarketCap = "https://api.coinmarketcap.com/v1/"
    private val netApi = RetrofitServiceGenerator(baseCoinMarketCap, NetApi::class.java).netApi

    override fun getCoins() = netApi.getCoins().observeOn(AndroidSchedulers.mainThread())

}