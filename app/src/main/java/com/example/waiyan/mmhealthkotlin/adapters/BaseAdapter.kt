package com.example.waiyan.mmhealthkotlin.adapters

import android.support.v7.widget.RecyclerView
import com.example.waiyan.mmhealthkotlin.data.vos.HealthCareInfoVO
import com.example.waiyan.mmhealthkotlin.viewholders.BaseViewHolder

abstract class BaseAdapter<VH, W> : RecyclerView.Adapter<BaseViewHolder<W>>() {

    protected var dataList: List<W> = ArrayList()

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<W>, position: Int) {
        holder.bindData(dataList[position])
    }

    fun setHealthCareInfoList(list: List<W>){
        dataList=list
        notifyDataSetChanged()
    }
}