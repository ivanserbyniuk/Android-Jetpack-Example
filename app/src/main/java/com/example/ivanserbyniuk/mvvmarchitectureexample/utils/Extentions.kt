package com.example.ivanserbyniuk.mvvmarchitectureexample.utils

import android.view.View
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

infix operator fun CompositeDisposable.plusAssign(subscribe: Disposable) {
    this.add(subscribe)
}

fun View.show(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}