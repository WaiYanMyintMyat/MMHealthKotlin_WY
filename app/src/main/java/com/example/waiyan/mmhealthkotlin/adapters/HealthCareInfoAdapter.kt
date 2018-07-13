package com.example.waiyan.mmhealthkotlin.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.waiyan.mmhealthkotlin.R
import com.example.waiyan.mmhealthkotlin.data.vos.HealthCareInfoVO
import com.example.waiyan.mmhealthkotlin.viewholders.BaseViewHolder
import com.example.waiyan.mmhealthkotlin.viewholders.HealthCareInfoViewHolder

class HealthCareInfoAdapter : BaseAdapter<HealthCareInfoViewHolder, HealthCareInfoVO>() {


    private var mHealthCareInfoList : List<HealthCareInfoVO>

    init {
        mHealthCareInfoList = ArrayList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<HealthCareInfoVO> {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_healthcare_info, parent, false)
        return HealthCareInfoViewHolder(view)
    }
}