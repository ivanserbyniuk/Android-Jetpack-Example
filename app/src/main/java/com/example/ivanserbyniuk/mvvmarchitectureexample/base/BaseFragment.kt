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
import kotlin.reflect.KClass

abstract class BaseFragment<T : BaseNetworkViewModel> : Fragment() {
    protected abstract val resId: Int
    protected abstract val viewModelClass: KClass<T>

    protected val viewModel: T by lazy {
        val viewModeBuilder = initVM
        return@lazy if (viewModeBuilder != null) viewModelByFactory(viewModeBuilder) else createViewModel()
    }

    protected open val initVM: (() -> T)? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(resId, container)
    }

    fun viewModelByFactory(viewModel: () -> T): T {
        return ViewModelProviders.of(this, CustomFactory(viewModel)).get(viewModelClass.java)
    }

    private fun createViewModel(): T {
        return ViewModelProviders.of(this).get(viewModelClass.java)
    }

    fun BaseNetworkViewModel.observBaseEvents(): BaseNetworkViewModel {
        progressData.observe { onProgress(it) }
        errorData.observe { onError(it) }
        return this
    }

    fun <T> LiveData<T>.observe(observer: Observer<T>) {
        this.observe(this@BaseFragment, observer)
    }

    fun <T> LiveData<T>.observe(observer: (T) -> Unit) {
        this.observe(this@BaseFragment, Observer {
            if (it != null) observer(it)
        })
    }

    fun <T> LiveData<T>.observeNullable(observer: (T?) -> Unit) {
        this.observe(this@BaseFragment, Observer { observer(it) })
    }

    abstract fun onProgress(isProgress: Boolean)

    abstract fun onError(throwable: Throwable)


}