package com.crypto.cryptocointracker.Adapter

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.crypto.cryptocointracker.Model.CoinModel
import kotlinx.android.synthetic.main.coin_layout.view.*

/**
 * Created by mrlucky on 16/3/18.
 */

class CoinViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
    var coinIcon = itemView.coinIcon
    var coinSymbol = itemView.coinSymbol
    var coinName = itemView.coinName
    var coinPrice = itemView.priceUsd
    var onHourChange = itemView.oneHour
    var twentyFourChange = itemView.twentyFour_Hour
    var sevenDaysChange = itemView.sevenDay

}


class coinAdapter(recyclerView: RecyclerView, activity: Activity,var items:List<CoinModel>): RecyclerView.Adapter<CoinViewHolder>() {

    

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CoinViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: CoinViewHolder?, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}