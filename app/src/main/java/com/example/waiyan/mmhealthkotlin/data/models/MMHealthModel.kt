package com.example.waiyan.mmhealthkotlin.data.models

import com.example.waiyan.mmhealthkotlin.data.vos.HealthCareInfoVO
import com.example.waiyan.mmhealthkotlin.events.SuccessGetHealthCareInfoEvent
import com.example.waiyan.mmhealthkotlin.network.MMHealthCareDataAgent
import com.example.waiyan.mmhealthkotlin.network.RetrofitDataAgentImpl
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MMHealthModel {
    companion object {
        private var instanceObj: MMHealthModel? = null

        private val ACCESS_TOKEN = "b002c7e1a528b7cb460933fc2875e916"

        private var mDataAgent: MMHealthCareDataAgent? = null

        fun getInstanceObject(): MMHealthModel? {

            if (instanceObj == null) {
                instanceObj = MMHealthModel()
            }
            return instanceObj
        }
    }


    private var mDataRepo: HashMap<Int, HealthCareInfoVO> = HashMap()

    private constructor() {
        mDataAgent = RetrofitDataAgentImpl.getInstanceObject()
        EventBus.getDefault().register(this)
    }

    fun loadHealthCareInfo() {
        mDataAgent!!.loadHealthCareInfo(ACCESS_TOKEN)
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    fun onSuccessGetHealthInfo(event: SuccessGetHealthCareInfoEvent) {
        setHealthInfoDataRepo(event.healthCareList)
    }

    fun setHealthInfoDataRepo(healthInfoList: List<HealthCareInfoVO>) {
        for (healthCare: HealthCareInfoVO in healthInfoList) {
            mDataRepo[healthCare.id] = healthCare
        }
    }

    fun getHealthInfoById(id: Int):HealthCareInfoVO?{
        return mDataRepo[id]
    }
}