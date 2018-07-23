package com.example.ivanserbyniuk.mvvmarchitectureexample.network.repositories

import com.example.ivanserbyniuk.mvvmarchitectureexample.network.models.Coin
import io.reactivex.Single

interface CoinsRepository {

    fun getCoins(): Single<List<Coin>>
    fun getFavorites(): Single<String>

}