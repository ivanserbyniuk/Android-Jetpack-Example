package com.example.ivanserbyniuk.mvvmarchitectureexample.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import com.example.ivanserbyniuk.mvvmarchitectureexample.R
import com.example.ivanserbyniuk.mvvmarchitectureexample.base.BaseFragment
import com.example.ivanserbyniuk.mvvmarchitectureexample.network.models.Coin
import com.example.ivanserbyniuk.mvvmarchitectureexample.utils.show
import com.example.ivanserbyniuk.mvvmarchitectureexample.viewmodels.CoinsViewModel
import com.example.ivanserbyniuk.mvvmarchitectureexample.viewmodels.RequestState
import kotlinx.android.synthetic.main.fragment_coins.*

class CoinsFragment : BaseFragment() {

    override val resId = R.layout.fragment_coins

    private lateinit var coinsViewModel: CoinsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        coinsViewModel = ViewModelProviders.of(this).get(CoinsViewModel::class.java)
        coinsViewModel.loadCoins()
                .observe(this, Observer { requestState ->
                    when (requestState) {
                        is RequestState.Progress -> progressBar.show(requestState.loading)
                        is RequestState.Success -> buildList(requestState.data)
                        is RequestState.Failure -> onError(requestState.e)
                    }
                })
    }

    private fun buildList(coins: List<Coin>) {
        rvCoins.setModels(coins.map { CoinsItemViewModel_().bind(it).id(it.id) })
    }

    private fun onError(throwable: Throwable) {

    }

}