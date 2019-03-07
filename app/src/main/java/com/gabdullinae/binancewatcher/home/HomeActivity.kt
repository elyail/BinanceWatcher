package com.gabdullinae.binancewatcher.home

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.gabdullinae.binancewatcher.R
import com.gabdullinae.binancewatcher.di.DI
import com.gabdullinae.binancewatcher.lifecycle.getViewModel
import com.gabdullinae.binancewatcher.lifecycle.observe
import com.gabdullinae.binancewatcher.lifecycle.viewModelFactory
import kotlinx.android.synthetic.main.home_activity.*

class HomeActivity : AppCompatActivity() {
    private lateinit var viewModel: HomeViewModel
    private val adapter = AllCurrenciesHomeAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)
        DI.app.inject(this)
        val viewModelFactory = viewModelFactory { DI.app.homeBuilder().build().homeViewModel() }
        viewModel = getViewModel(viewModelFactory)
        observe(viewModel.data, ::onDataReceived)
        observe(viewModel.state, ::onStateReceived)
        initViews()
    }

    private fun onStateReceived(homeState: HomeState) {
        when (homeState) {
            HomeProgress -> {
                home_progress.visibility = View.VISIBLE
                error_content.visibility = View.GONE
            }
            is HomeError -> {
                home_progress.visibility = View.GONE
                screen_content.visibility = View.GONE
                error_content.visibility = View.VISIBLE
                home_error_title.text = homeState.errorMessage
            }
            HomeShowContent -> {
                home_progress.visibility = View.GONE
                error_content.visibility = View.GONE
                screen_content.visibility = View.VISIBLE
            }
        }
    }

    private fun initViews() {
        fragment_all_currencies_list.adapter = adapter
        fragment_all_currencies_list.layoutManager = LinearLayoutManager(this)
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
