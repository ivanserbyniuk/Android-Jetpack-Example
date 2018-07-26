package com.example.ivanserbyniuk.mvvmarchitectureexample.viewmodels.base

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.SingleTransformer
import io.reactivex.disposables.CompositeDisposable


abstract class BaseNetworkViewModel : ViewModel() {
    protected val dontRepeatError = false
    protected val compDis = CompositeDisposable()
    val progressData = MutableLiveData<Boolean>()
    val errorData = MutableLiveData<Throwable>()

    protected fun progress(inProgress: Boolean) {
        progressData.value = inProgress
    }

    protected fun error(throwable: Throwable) {
        errorData.value = throwable
        if (dontRepeatError) {
            errorData.value
        }
    }

    override fun onCleared() {
        super.onCleared()
        compDis.clear()
    }

    fun <T> progressTransformer() = SingleTransformer<T, T> { upstream ->
        upstream.doOnSubscribe { progress(true) }
                .doFinally { progress(false) }
    }

}