package com.example.ivanserbyniuk.mvvmarchitectureexample.base

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ivanserbyniuk.mvvmarchitectureexample.viewmodels.BaseNetworkViewModel


abstract class BaseFragment<T : BaseNetworkViewModel> : Fragment() {
    protected abstract val resId:Int
    protected lateinit var viewModel: T
    abstract val viewModelClass: Class<T>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(resId, container)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(viewModelClass)
        viewModel.progressData.observe(this, Observer { onProgress(it ?: false) })
        viewModel.errorData.observe(this, Observer { onError(it!!) })
    }

    abstract fun onProgress(isProgress: Boolean)

    abstract fun onError(throwable: Throwable)

}