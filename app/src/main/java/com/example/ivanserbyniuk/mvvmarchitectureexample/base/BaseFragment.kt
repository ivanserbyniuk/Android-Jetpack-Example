package com.example.ivanserbyniuk.mvvmarchitectureexample.base

import android.arch.lifecycle.LiveData
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
        viewModel = ViewModelProviders.of(this).get(viewModelClass).apply {
            progressData.observe(Observer { onProgress(it ?: false) })
            errorData.observe(Observer { onError(it!!) })
        }
    }

    fun <T> LiveData<T>.observe(observer: Observer<T>) {
        this.observe(this@BaseFragment, observer)
    }


    fun <T> LiveData<T>.observe(observer: (T?) -> Unit) {
        this.observe(this@BaseFragment, Observer { observer(it) })
    }

    abstract fun onProgress(isProgress: Boolean)

    abstract fun onError(throwable: Throwable)

}