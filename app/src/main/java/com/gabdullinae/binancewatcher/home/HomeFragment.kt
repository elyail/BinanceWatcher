package com.gabdullinae.binancewatcher.home

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.gabdullinae.binancewatcher.BaseFragment
import com.gabdullinae.binancewatcher.R
import com.gabdullinae.binancewatcher.di.DI
import com.gabdullinae.binancewatcher.di.components.AppComponent
import com.gabdullinae.binancewatcher.di.components.DaggerAppComponent
import com.gabdullinae.binancewatcher.lifecycle.getViewModel
import com.gabdullinae.binancewatcher.lifecycle.observe
import com.gabdullinae.binancewatcher.lifecycle.viewModelFactory
import kotlinx.android.synthetic.main.fragment_all_currencies.*

class HomeFragment : BaseFragment() {

    override var layoutResId: Int = R.layout.fragment_all_currencies

    private lateinit var viewModel: HomeViewModel
    private val adapter = AllCurrenciesHomeAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModelFactory = viewModelFactory {  DI.app.homeBuilder().build().homeViewModel() }
        viewModel = getViewModel(viewModelFactory)
        observe(viewModel.data, ::onDataReceived)
        initViews()
    }

    private fun initViews() {
        fragment_all_currencies_list.adapter = adapter
        fragment_all_currencies_list.layoutManager = LinearLayoutManager(context)
    }

    private fun onDataReceived(data: HomeData) {
        when (data) {
            is CurrenciesInfoState -> {
                adapter.items = data.items
                adapter.notifyDataSetChanged()
            }
        }
    }
}