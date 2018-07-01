package com.example.ivanserbyniuk.mvvmarchitectureexample.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.ivanserbyniuk.mvvmarchitectureexample.network.NetApiClient
import com.example.ivanserbyniuk.mvvmarchitectureexample.network.models.Coin
import com.example.ivanserbyniuk.mvvmarchitectureexample.utils.plusAssign
import io.reactivex.disposables.CompositeDisposable

class CoinsViewModel : ViewModel() {
    private val compDis = CompositeDisposable()
    private val responseLiveData = MutableLiveData<RequestState<List<Coin>>>()

    fun loadCoins(): LiveData<RequestState<List<Coin>>> {
        compDis += NetApiClient().getCoins()
                .doOnSubscribe { responseLiveData.value = RequestState.loading(true) }
                .doFinally { responseLiveData.value = RequestState.loading(false) }
                .subscribe(
                        { responseLiveData.value = RequestState.success(it) },
                        { responseLiveData.value = RequestState.failure(it) })
        return responseLiveData
    }

    override fun onCleared() {
        super.onCleared()
        compDis.clear()
    }
}