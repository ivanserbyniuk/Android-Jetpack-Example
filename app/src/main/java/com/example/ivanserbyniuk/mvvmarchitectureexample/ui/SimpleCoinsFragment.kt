package com.example.ivanserbyniuk.mvvmarchitectureexample.ui

import android.os.Bundle
import android.view.View
import com.example.ivanserbyniuk.mvvmarchitectureexample.R
import com.example.ivanserbyniuk.mvvmarchitectureexample.base.BaseFragment
import com.example.ivanserbyniuk.mvvmarchitectureexample.viewmodels.SimpleContViewModel

class SimpleCoinsFragment : BaseFragment<SimpleContViewModel>() {

    override val resId = R.layout.fragment_coins
    override val viewModelClass = SimpleContViewModel::class.java

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.resultLiveData.observe { result -> }
        viewModel.makeRequest()
    }

    override fun onProgress(isProgress: Boolean) {
    }

    override fun onError(throwable: Throwable) {
    }
}