package com.example.ivanserbyniuk.mvvmarchitectureexample.viewmodels

import com.example.ivanserbyniuk.mvvmarchitectureexample.network.models.Coin
import com.example.ivanserbyniuk.mvvmarchitectureexample.network.repositories.CoinsRepositoryNetwork

class ComplexViewModel : BeOperationViewModel<List<Coin>, String>() {

    val coinsData get() = primaryResultLiveData
    val favoritesData get() = secondaryyResultLiveData

    fun makeCoinRequest() {
        CoinsRepositoryNetwork().getCoins()
                .compose(progressTransformer())
                .subscribe(primaryConsumer)
    }

    fun getFavorites() {
        CoinsRepositoryNetwork().getFavorites()
                .compose(progressTransformer())
                .subscribe(secondartyConsumer)
    }
}