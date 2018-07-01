package com.example.ivanserbyniuk.mvvmarchitectureexample.network.models

import com.google.gson.annotations.SerializedName

data class Coin (
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("symbol")
    val symbol: String? = null,
    @SerializedName("rank")
    val rank: String? = null,
    @SerializedName("price_usd")
    val priceUsd: String? = null,
    @SerializedName("price_btc")
    val priceBtc: String? = null,
    @SerializedName("24h_volume_usd")
    val _24hVolumeUsd: String? = null,
    @SerializedName("market_cap_usd")
    val marketCapUsd: String? = null,
    @SerializedName("available_supply")
    val availableSupply: String? = null,
    @SerializedName("total_supply")
    val totalSupply: String? = null,
    @SerializedName("max_supply")
    val maxSupply: String? = null,
    @SerializedName("percent_change_1h")
    val percentChange1h: String? = null,
    @SerializedName("percent_change_24h")
    val percentChange24h: String? = null,
    @SerializedName("percent_change_7d")
    val percentChange7d: String? = null,
    @SerializedName("last_updated")
    val lastUpdated: String? = null
)


