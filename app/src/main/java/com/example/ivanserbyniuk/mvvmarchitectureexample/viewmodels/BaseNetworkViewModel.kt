package com.example.ivanserbyniuk.mvvmarchitectureexample.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseNetworkViewModel : ViewModel() {

    protected val compDis = CompositeDisposable()
    val progressData = MutableLiveData<Boolean>()
    val errorData = MutableLiveData<Throwable>()

    protected fun progress(inProgress: Boolean) {
        progressData.value = inProgress
    }

    protected fun error(throwable: Throwable) {
        errorData.value = throwable
    }

    override fun onCleared() {
        super.onCleared()
        compDis.clear()
    }
}