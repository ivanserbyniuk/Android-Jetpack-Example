package com.example.ivanserbyniuk.mvvmarchitectureexample.viewmodels

import android.util.Log
import com.example.ivanserbyniuk.mvvmarchitectureexample.network.models.Coin
import com.example.ivanserbyniuk.mvvmarchitectureexample.network.repositories.CoinsRepositoryNetwork

class SimpleContViewModel : SimpleNetworkViewModel<List<Coin>?>() {

    override fun makeRequest() {
        CoinsRepositoryNetwork().getCoins()
                .compose(progressTransformer())
                .doOnSuccess { it -> Log.d("myLogs", " size " + it.size) }
                .subscribe(defaultConsumer)
    }

}