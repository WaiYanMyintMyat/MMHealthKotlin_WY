package com.example.waiyan.mmhealthkotlin.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.waiyan.mmhealthkotlin.R
import com.example.waiyan.mmhealthkotlin.viewholders.HealthCareInfoViewHolder

class HealthCareInfoAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_healthcare_info, parent, false)
        return HealthCareInfoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 13
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }
}