package com.example.ivanserbyniuk.mvvmarchitectureexample.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.ivanserbyniuk.mvvmarchitectureexample.utils.plusAssign
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable

abstract class SimpleNetworkViewModel<T> : BaseNetworkViewModel() {

    private val _result = MutableLiveData<T>()
    protected val defaultConsumer = object : SingleObserver<T> {
        override fun onSuccess(result: T) {
            success(result)
        }

        override fun onSubscribe(disposable: Disposable) {
            compDis += disposable
        }

        override fun onError(throwable: Throwable) {
            error(throwable)
        }

    }

    val resultLiveData: LiveData<T> get() = _result

    fun success(result: T) {
        _result.value = result
    }

    abstract fun makeRequest()
}