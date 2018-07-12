package com.example.ivanserbyniuk.mvvmarchitectureexample.base

import android.arch.lifecycle.*
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ivanserbyniuk.mvvmarchitectureexample.viewmodels.BaseNetworkViewModel


abstract class BaseFragment : Fragment() {
    protected abstract val resId: Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(resId, container)
    }

    inline fun <reified T : BaseNetworkViewModel> viewModel(): T {
        return observBaseEvents(ViewModelProviders.of(this).get(T::class.java))
    }

    inline fun <reified T : BaseNetworkViewModel> viewModelByFactory(noinline viewModel: () -> T): T {
        return observBaseEvents(ViewModelProviders.of(this, CustomFactory(viewModel)).get(T::class.java))
    }

    inline fun <reified T : BaseNetworkViewModel> viewModelByFactory(factory: ViewModelProvider.Factory): T {
        return observBaseEvents(ViewModelProviders.of(this, factory).get(T::class.java))
    }

    public fun <T : BaseNetworkViewModel> observBaseEvents(networkViewModel: T) =
            networkViewModel.apply {
                progressData.observe { onProgress(it) }
                errorData.observe { onError(it) }
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


    class CustomFactory(private val viewModel: () -> ViewModel) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return viewModel() as T
        }
    }
}