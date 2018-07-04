package com.example.ivanserbyniuk.mvvmarchitectureexample.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.ivanserbyniuk.mvvmarchitectureexample.network.NetApiClient
import com.example.ivanserbyniuk.mvvmarchitectureexample.network.models.Coin
import com.example.ivanserbyniuk.mvvmarchitectureexample.utils.plusAssign

class CoinViewModel2 : BaseNetworkViewModel() {

    private val responseData = MutableLiveData<List<Coin>>()

    fun loadCoins(): LiveData<List<Coin>> {
        compDis += NetApiClient().getCoins()
                .doOnSubscribe { progress(true) }
                .doFinally { progress(false) }
                .subscribe({ responseData.value = it }, { error(it) })
        return responseData
    }


}