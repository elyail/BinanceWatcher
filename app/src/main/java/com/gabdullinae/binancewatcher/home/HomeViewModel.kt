package com.gabdullinae.binancewatcher.home

import android.os.Handler
import androidx.lifecycle.MutableLiveData
import com.gabdullinae.binancewatcher.R
import com.gabdullinae.binancewatcher.lifecycle.BaseViewModel
import com.gabdullinae.binancewatcher.lifecycle.onNext
import com.gabdullinae.data.utils.Resources
import com.gabdullinae.domain.entity.CurrencyInfo
import com.gabdullinae.domain.usecases.HomeUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class HomeViewModel(
        val useCase: HomeUseCase,
        val resources: Resources) : BaseViewModel() {

    var data = MutableLiveData<HomeData>()
    var state = MutableLiveData<HomeState>()

    init {
        val handler = Handler()
        val runnable = object : Runnable {
            override fun run() {
                getData()
                handler.postDelayed(this, 3000)
            }
        }
        handler.postDelayed(runnable, 3000)
    }

    fun getData() {
        state.onNext(HomeProgress)
        useCase.getAllCurrenciesInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { onSuccess(it) },
                        { onError(it) })
                .disposeOnViewModelDestroy()
    }

    private fun onSuccess(items: List<CurrencyInfo>) {
        data.onNext(CurrenciesInfoState(items))
        state.onNext(HomeShowContent)
    }

    private fun onError(throwable: Throwable) {
        state.onNext(HomeError(throwable.localizedMessage
                ?: resources.getString(R.string.common_error)))
    }
}