package com.example.ivanserbyniuk.mvvmarchitectureexample.network

import com.example.ivanserbyniuk.mvvmarchitectureexample.network.models.Coin
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET

interface NetApi {

    @GET("ticker/?limit=100")
    fun getCoins(): Single<List<Coin>>
}