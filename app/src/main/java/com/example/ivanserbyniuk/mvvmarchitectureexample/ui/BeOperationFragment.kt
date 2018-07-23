package com.example.ivanserbyniuk.mvvmarchitectureexample.ui

import android.os.Bundle
import android.view.View
import com.example.ivanserbyniuk.mvvmarchitectureexample.base.BaseFragment
import com.example.ivanserbyniuk.mvvmarchitectureexample.viewmodels.ComplexViewModel

class BeOperationFragment : BaseFragment<ComplexViewModel>() {

    override val resId = 0

    override val viewModelClass = ComplexViewModel::class

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.coinsData.observe { }
        viewModel.favoritesData.observe { }

        viewModel.makeCoinRequest()
        viewModel.getFavorites()
    }

    override fun onProgress(isProgress: Boolean) {
    }

    override fun onError(throwable: Throwable) {
    }

}