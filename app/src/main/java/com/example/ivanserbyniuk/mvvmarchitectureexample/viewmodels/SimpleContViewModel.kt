package com.example.ivanserbyniuk.mvvmarchitectureexample.viewmodels

import com.example.ivanserbyniuk.mvvmarchitectureexample.network.models.Coin
import com.example.ivanserbyniuk.mvvmarchitectureexample.network.repositories.CoinsRepositoryNetwork

class SimpleContViewModel : SimpleNetworkViewModel<List<Coin>?>() {

    override fun makeRequest() {
        CoinsRepositoryNetwork().getCoins()
                .compose(progressTransformer())
                .subscribe(defaultConsumer)
    }
}