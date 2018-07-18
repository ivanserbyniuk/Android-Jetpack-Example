package com.example.ivanserbyniuk.mvvmarchitectureexample.base

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment

inline fun <reified T : ViewModel> Fragment.viewModel(): T {
    return ViewModelProviders.of(this).get(T::class.java)
}

inline fun <reified T : ViewModel> Fragment.viewModelByFactory(noinline viewModel: () -> T): T {
    return ViewModelProviders.of(this, CustomFactory(viewModel)).get(T::class.java)
}

inline fun <reified T : ViewModel> Fragment.viewModelByFactory(factory: ViewModelProvider.Factory): T {
    return ViewModelProviders.of(this, factory).get(T::class.java)
}


class CustomFactory(private val viewModel: () -> ViewModel) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return viewModel() as T
    }
}
