package com.example.ivanserbyniuk.mvvmarchitectureexample.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.ivanserbyniuk.mvvmarchitectureexample.network.models.Coin
import com.example.ivanserbyniuk.mvvmarchitectureexample.network.repositories.CoinsRepositoryNetwork
import com.example.ivanserbyniuk.mvvmarchitectureexample.utils.plusAssign

class CoinViewModel2(val initValue: String = "") : BaseNetworkViewModel() {

    private val responseData = MutableLiveData<List<Coin>>()
    private val coinsRepository = CoinsRepositoryNetwork()

    fun loadCoins(): LiveData<List<Coin>> {
        compDis += coinsRepository.getCoins()
                .compose(progressTransformer())
                .subscribe({ responseData.value = it }, { error(it) })
        return responseData
    }


}