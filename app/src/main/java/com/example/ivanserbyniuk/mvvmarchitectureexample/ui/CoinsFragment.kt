package com.example.ivanserbyniuk.mvvmarchitectureexample.ui

import android.os.Bundle
import android.view.View
import com.example.ivanserbyniuk.mvvmarchitectureexample.R
import com.example.ivanserbyniuk.mvvmarchitectureexample.base.BaseFragment
import com.example.ivanserbyniuk.mvvmarchitectureexample.network.models.Coin
import com.example.ivanserbyniuk.mvvmarchitectureexample.utils.show
import com.example.ivanserbyniuk.mvvmarchitectureexample.viewmodels.CoinViewModel2
import kotlinx.android.synthetic.main.fragment_coins.*

class CoinsFragment : BaseFragment() {

    override val resId = R.layout.fragment_coins

    private val viewModel by lazy { viewModel<CoinViewModel2>() }
    // or use viewModelByFactory { CoinViewModel2("Factory") } , if you want create viewModel with
    // custom constructor

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadCoins().observe { buildList(it) }
    }

    private fun buildList(coins: List<Coin>) {
        rvCoins.setModels(coins.map { CoinsItemViewModel_().bind(it).id(it.id) })
    }

    override fun onProgress(isProgress: Boolean) = progressBar.show(isProgress)


    override fun onError(throwable: Throwable) = throwable.printStackTrace()


}