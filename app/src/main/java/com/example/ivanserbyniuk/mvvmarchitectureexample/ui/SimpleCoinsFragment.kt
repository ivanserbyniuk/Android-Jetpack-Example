package com.example.ivanserbyniuk.mvvmarchitectureexample.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.ivanserbyniuk.mvvmarchitectureexample.R
import com.example.ivanserbyniuk.mvvmarchitectureexample.base.BaseFragment
import com.example.ivanserbyniuk.mvvmarchitectureexample.viewmodels.SimpleContViewModel

class SimpleCoinsFragment : BaseFragment<SimpleContViewModel>() {

    override val resId = R.layout.fragment_coins
    override val viewModelClass = SimpleContViewModel::class.java

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.resultLiveData.observe { result -> Log.d("myLogs", "res  " + result?.size) }
        viewModel.makeRequest()
    }

    override fun onProgress(isProgress: Boolean) {
    }

    override fun onError(throwable: Throwable) {
        Toast.makeText(activity, " Error", Toast.LENGTH_LONG).show()
    }
}