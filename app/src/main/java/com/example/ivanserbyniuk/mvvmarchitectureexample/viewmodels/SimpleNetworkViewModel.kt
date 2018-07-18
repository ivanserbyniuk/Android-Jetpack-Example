package com.example.ivanserbyniuk.mvvmarchitectureexample.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import io.reactivex.functions.BiConsumer

abstract class SimpleNetworkViewModel<T> : BaseNetworkViewModel() {
    private val _result = MutableLiveData<T>()
    protected val defaultConsumer = BiConsumer { result: T, error: Throwable ->
        success(result)
        error(error)
    }

    val resultLiveData: LiveData<T> get() = _result

    fun success(result: T) {
        _result.value = result
    }

    abstract fun makeRequest()
}