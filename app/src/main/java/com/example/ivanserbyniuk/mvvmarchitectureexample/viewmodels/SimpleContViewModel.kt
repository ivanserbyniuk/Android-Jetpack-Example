package com.example.ivanserbyniuk.mvvmarchitectureexample.viewmodels

import com.example.ivanserbyniuk.mvvmarchitectureexample.network.models.Coin
import com.example.ivanserbyniuk.mvvmarchitectureexample.network.repositories.CoinsRepositoryNetwork
import com.example.ivanserbyniuk.mvvmarchitectureexample.utils.plusAssign

class SimpleContViewModel : SimpleNetworkViewModel<List<Coin>>() {

    override fun makeRequest() {
        compDis += CoinsRepositoryNetwork().getCoins()
                .compose(progressTransformer())
                .subscribe(defaultConsumer)
    }

}