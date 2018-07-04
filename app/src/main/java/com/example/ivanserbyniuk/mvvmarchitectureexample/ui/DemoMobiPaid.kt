package com.example.ivanserbyniuk.mvvmarchitectureexample.ui

import android.arch.lifecycle.MutableLiveData
import android.os.Bundle
import android.view.View
import com.example.ivanserbyniuk.mvvmarchitectureexample.base.BaseFragment
import com.example.ivanserbyniuk.mvvmarchitectureexample.viewmodels.BaseNetworkViewModel
import org.json.JSONObject

class DemoMobiPaid {

    class MobiPaidFragment : BaseFragment<MobiModiViewModel>() {
        override val resId: Int = 0
        override val viewModelClass = MobiModiViewModel::class.java

        override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            viewModel.run {
                onArchiveLoadedData.observe(::onAchivesLoaded)
                operatorLoadedData.observe(::onOperatorsLoaded)
                onPaidFinishedData.observe(::onPaid)
                isUaNumberData.observe { isUAOperators ->
                    //change ui
                }

            }
            viewModel.checkNumber()
            viewModel.next()
        }

        fun onAchivesLoaded(data: Any) {

        }

        fun onOperatorsLoaded(data: Any) {

        }

        fun onPaid(data: Any) {

        }


        override fun onProgress(isProgress: Boolean) {
        }

        override fun onError(throwable: Throwable) {
        }


    }

    class MobiModiViewModel : BaseNetworkViewModel() {
        var number: String = ""
        var isUkrainian = true
        val isUkrainianNumberAspect: (String) -> Boolean = { it.startsWith("+380") }
        val mobyPaidHelper = MobyPaidHelper()

        val isUaNumberData = MutableLiveData<Boolean>()
        val onPaidFinishedData = MutableLiveData<JSONObject>()
        val onArchiveLoadedData = MutableLiveData<Any>()
        val operatorLoadedData = MutableLiveData<Any>()

        fun next() {
            val previosValue = isUkrainian
            val currentValue = isUAOperator()
            if (currentValue && previosValue)
                mobyPaidHelper.mobiPay(buildParams(), { onPaidFinishedData.value = (it) })
            else if (!currentValue && previosValue) {
                isUkrainian = false
                applyState()
            } else if (!currentValue && !previosValue) {
                mobyPaidHelper.mobiPay(buildParams(), { onPaidFinishedData.value = (it) })
            } else if (currentValue && !previosValue) {
                isUkrainian = true
                applyState()
            }
        }

        private fun applyState() {
            isUaNumberData.value = isUkrainian
        }

        fun getOperatorsByPhone() {
            if (number.length < 4) return
            if (isUAOperator()) {
                isUaNumberData.value = true
            } else {
                progress(true)
                mobyPaidHelper.getOperator(buildParams(), {
                    progress(false)
                    isUaNumberData.value = true
                    operatorLoadedData.value = ""

                })
            }
        }

        fun changeSum(sum: Double) {

        }

        fun changeCurrency(currency: String) {

        }

        fun changeCard(card: String) {

        }

        fun checkNumber() {
            isUkrainian = isUAOperator()
            applyState()
        }

        fun isUAOperator() = isUkrainianNumberAspect(number)

        fun buildParams() = MobiPaidParams(number)

        fun loadNumbersFromArchive() {
            progress(true)
            mobyPaidHelper.archive {
                progress(false)
                onArchiveLoadedData.value = it
            }
        }


        class MobyPaidHelper {

        fun archive(callback: (List<Any>) -> Unit) {
            callback(arrayListOf())
        }

        fun getOperator(params: MobiPaidParams, callback: () -> Unit) {
            callback()
        }

        fun mobiPay(params: MobiPaidParams, callback: (JSONObject?) -> Unit) {
            callback(null)
        }
    }

    class MobiPaidParams(
            val number: String,
            val action: String? = null,
            val cardId: String? = null,
            val operatorId: String? = null,
            val currency: String? = null,
            val amount: String? = null
    )

}