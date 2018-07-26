package com.example.ivanserbyniuk.mvvmarchitectureexample.viewmodels.base

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.ivanserbyniuk.mvvmarchitectureexample.utils.plusAssign
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable

abstract class BeOperationViewModel<P, S> : BaseNetworkViewModel() {

    private val _primaryResult = MutableLiveData<P>()
    private val _secondaryResult = MutableLiveData<S>()


    protected val primaryConsumer = createConsumer<P> { _primaryResult.value = it }
    protected val secondartyConsumer = createConsumer<S> { _secondaryResult.value = it }

    protected val primaryResultLiveData: LiveData<P> get() = _primaryResult
    protected val secondaryyResultLiveData: LiveData<S> get() = _secondaryResult

    fun <I> createConsumer(success: (I) -> Unit) = object : SingleObserver<I> {
        override fun onSuccess(result: I) {
            success(result)
        }

        override fun onSubscribe(disposable: Disposable) {
            compDis += disposable
        }

        override fun onError(throwable: Throwable) {
            error(throwable)
        }
    }

}