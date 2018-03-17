package com.crypto.cryptocointracker.Adapter

import android.app.Activity
import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.crypto.cryptocointracker.Common.Common
import com.crypto.cryptocointracker.Interface.ILoadMore
import com.crypto.cryptocointracker.Model.CoinModel
import com.crypto.cryptocointracker.R
import com.squareup.picasso.Picasso
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


class CoinAdapter(recyclerView: RecyclerView, internal var activity: Activity,var items:List<CoinModel>): RecyclerView.Adapter<CoinViewHolder>() {

    internal var loadMore:ILoadMore?=null
    var isLoadng:Boolean=false
    var visiableThreshold=5
    var lastVisiableItem:Int=0
    var totalItemCount:Int=0
    init {
        val linearLayout = recyclerView.layoutManager as LinearLayoutManager
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                totalItemCount = linearLayout.itemCount
                lastVisiableItem = linearLayout.findLastVisibleItemPosition()
                if(!isLoadng && totalItemCount <= lastVisiableItem+visiableThreshold){
                    if(loadMore != null){
                        loadMore!!.onLoadMore()
                    }
                    isLoadng = true
                }
            }
        })
    }

    fun setLoadMore(loadMore:ILoadMore){
        this.loadMore = loadMore
    }


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CoinViewHolder {
        val view = LayoutInflater.from(activity)
                .inflate(R.layout.coin_layout,parent,false)
        return CoinViewHolder(view)
    }

    override fun getItemCount(): Int {
        return  items.size
    }

    override fun onBindViewHolder(holder: CoinViewHolder?, position: Int) {
        val coinModel = items.get(position)

        val item = holder as CoinViewHolder

        item.coinName.text = coinModel.name
        item.coinSymbol.text = coinModel.symbol
        item.coinPrice.text = coinModel.price_usd
        item.onHourChange.text = coinModel.percent_change_1h+"%"
        item.twentyFourChange.text = coinModel.percent_change_24h+"%"
        item.sevenDaysChange.text = coinModel.percent_change_7d+"%"

        Picasso.with(activity.baseContext)
                .load(StringBuilder(Common.imageUrl).append(coinModel.symbol!!.toLowerCase())
                        .append(".png")
                        .toString()).into(item.coinIcon)

        Log.d("URl",StringBuilder(Common.imageUrl).append(coinModel.symbol!!.toLowerCase())
                .append(".png")
                .toString())


        //set color
        item.onHourChange.setTextColor(if (coinModel.percent_change_1h!!.contains("-"))
                                            Color.parseColor("#FF0000")
                                      else
                                            Color.parseColor("#32cd32"))
        item.twentyFourChange.setTextColor(if (coinModel.percent_change_24h!!.contains("-"))
            Color.parseColor("#FF0000")
        else
            Color.parseColor("#32cd32"))

        item.sevenDaysChange.setTextColor(if (coinModel.percent_change_7d!!.contains("-"))
            Color.parseColor("#FF0000")
        else
            Color.parseColor("#32cd32"))

    }

    fun setLoaded(){
        isLoadng = false
    }

    fun updateData(coinModels:List<CoinModel>){
        this.items = coinModels
        notifyDataSetChanged()

    }

}