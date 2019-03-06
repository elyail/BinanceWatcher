package com.gabdullinae.binancewatcher.home

import androidx.lifecycle.MutableLiveData
import com.gabdullinae.binancewatcher.lifecycle.BaseViewModel
import com.gabdullinae.binancewatcher.lifecycle.onNext
import com.gabdullinae.domain.entity.CurrencyInfo
import com.gabdullinae.domain.usecases.HomeUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomeViewModel(val useCase: HomeUseCase) : BaseViewModel() {

    var data = MutableLiveData<HomeData>()

    init {
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
    }

    private fun onError(throwable: Throwable) {

    }

}