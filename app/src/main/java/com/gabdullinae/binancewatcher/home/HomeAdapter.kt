package com.gabdullinae.binancewatcher.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gabdullinae.binancewatcher.R
import com.gabdullinae.domain.entity.CurrencyInfo

class AllCurrenciesHomeAdapter : RecyclerView.Adapter<CurrenciesViewHolder>() {
    var items: List<CurrencyInfo> = listOf()
    var context: Context? = null

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        context = recyclerView.context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrenciesViewHolder =
            CurrenciesViewHolder(LayoutInflater.from(context).inflate(R.layout.item_currencies_info, parent, false))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CurrenciesViewHolder, position: Int) {
        holder.bindData(items[position])
    }
}

class CurrenciesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val firstCurrency = itemView.findViewById<TextView>(R.id.first_currency)
    private val secondCurrency = itemView.findViewById<TextView>(R.id.second_currency)
    private val status = itemView.findViewById<TextView>(R.id.status)

    fun bindData(info: CurrencyInfo) {
        firstCurrency.text = info.firstCurrency
        secondCurrency.text = info.secondCurrency
        status.text = info.status
    }
}