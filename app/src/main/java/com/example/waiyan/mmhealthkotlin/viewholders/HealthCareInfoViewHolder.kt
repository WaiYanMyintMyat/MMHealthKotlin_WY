package com.example.waiyan.mmhealthkotlin.viewholders

import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import com.example.waiyan.mmhealthkotlin.R
import com.example.waiyan.mmhealthkotlin.data.vos.HealthCareInfoVO
import kotlinx.android.synthetic.main.view_holder_healthcare_info.view.*

class HealthCareInfoViewHolder(itemView: View) : BaseViewHolder<HealthCareInfoVO>(itemView) {

    override fun bindData(data: HealthCareInfoVO) {
        mData=data
        itemView.tv_Title.text = data.title
        itemView.tv_pretty.text = if (data!!.infoType != "") {
            data!!.infoType
        } else {
            "Other"
        }
        itemView.tv_name.text = if (data.authorVO!!.authorName != "") {
            data.authorVO!!.authorName
        } else {
            "No Author"
        }

        if (data.image != "") {
            Glide.with(itemView.iv_hero)
                    .load(data.image)
                    .into(itemView.iv_hero)
        }
    }

    override fun onClick(v: View?) {

    }

}