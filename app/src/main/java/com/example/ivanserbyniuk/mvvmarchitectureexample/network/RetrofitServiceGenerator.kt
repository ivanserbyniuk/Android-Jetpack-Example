package com.example.ivanserbyniuk.mvvmarchitectureexample.network

import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitServiceGenerator <out T>(endpoint: String, api: Class<T>) {
    val netApi: T

    init {
        val restAdapter = Retrofit.Builder()
                .baseUrl(endpoint)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(GsonConverterFactory.create())
                .client(createClient())
                .build()
        netApi = restAdapter.create(api)
    }

    private fun createClient(): OkHttpClient {
        val httpClient = OkHttpClient.Builder().apply {
             connectTimeout(30, TimeUnit.SECONDS)
             readTimeout(30, TimeUnit.SECONDS)
        }
        return httpClient.build()
    }
}
