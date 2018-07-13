package com.example.waiyan.mmhealthkotlin.activities

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.example.waiyan.mmhealthkotlin.R
import com.example.waiyan.mmhealthkotlin.adapters.HealthCareInfoAdapter
import com.example.waiyan.mmhealthkotlin.data.models.MMHealthModel
import com.example.waiyan.mmhealthkotlin.events.ApiErrorEvent
import com.example.waiyan.mmhealthkotlin.events.SuccessGetHealthCareInfoEvent
import com.example.waiyan.mmhealthkotlin.viewpods.EmptyViewPod

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : AppCompatActivity() {

    private var adapter: HealthCareInfoAdapter? = null
    private var viewPodEmpty: EmptyViewPod? = null
    private var snackBar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        rv.layoutManager = LinearLayoutManager(applicationContext
                , LinearLayoutManager.VERTICAL
                , false)
        adapter = HealthCareInfoAdapter()
        rv.adapter = adapter

        swipeRefresh.isRefreshing = true
        MMHealthModel.getInstanceObject()!!.loadHealthCareInfo()

        swipeRefresh.setOnRefreshListener {
            MMHealthModel.getInstanceObject()!!.loadHealthCareInfo()
        }

        viewPodEmpty = vp_empty as EmptyViewPod
        viewPodEmpty!!.setEmptyData(R.drawable.empty_data, "No Data Avaliable")

    }

    override fun onStart() {
        super.onStart()
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }
    }

    override fun onStop() {
        super.onStop()
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this)
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onSuccessGetHealthCareList(successEvent: SuccessGetHealthCareInfoEvent) {
        swipeRefresh.isRefreshing = false
        viewPodEmpty!!.visibility=View.GONE

        adapter!!.setHealthCareInfoList(successEvent.healthCareList)
        if(snackBar!=null){
            snackBar!!.dismiss()
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onFailGeHealthCareInfo(errorEvent: ApiErrorEvent) {
        viewPodEmpty!!.visibility = View.VISIBLE
        swipeRefresh.isRefreshing = false

        snackBar = Snackbar.make(rv,errorEvent.errorMessage,Snackbar.LENGTH_INDEFINITE)
        snackBar!!.show()
    }
}
