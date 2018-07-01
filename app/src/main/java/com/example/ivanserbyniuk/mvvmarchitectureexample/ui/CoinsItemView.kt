package com.example.ivanserbyniuk.mvvmarchitectureexample.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.example.ivanserbyniuk.mvvmarchitectureexample.R
import com.example.ivanserbyniuk.mvvmarchitectureexample.network.models.Coin
import kotlinx.android.synthetic.main.list_item_coins.view.*

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class CoinsItemView  : FrameLayout {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    init {
        LayoutInflater.from(context).inflate(R.layout.list_item_coins, this)
    }

    @ModelProp
    fun bind(coin: Coin) {
        tvName.text = coin.name
    }
}