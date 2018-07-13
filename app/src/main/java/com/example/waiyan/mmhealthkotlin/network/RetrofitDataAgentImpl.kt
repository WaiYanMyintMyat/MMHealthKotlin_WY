package com.example.waiyan.mmhealthkotlin.network

import com.example.waiyan.mmhealthkotlin.events.ApiErrorEvent
import com.example.waiyan.mmhealthkotlin.events.SuccessGetHealthCareInfoEvent
import com.example.waiyan.mmhealthkotlin.network.responses.GetHealthCareResponse
import com.example.waiyan.mmhealthkotlin.utils.MMHealthCareConstants
import com.google.gson.Gson
import okhttp3.OkHttpClient
import org.greenrobot.eventbus.EventBus
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitDataAgentImpl : MMHealthCareDataAgent {

    companion object {
        private var instanceObj: RetrofitDataAgentImpl? = null

        fun getInstanceObject(): RetrofitDataAgentImpl? {
            if (instanceObj == null) {
                instanceObj = RetrofitDataAgentImpl()
            }
            return instanceObj
        }
    }

    private var mTheApi: MMHealthCareApi? = null

    private constructor() {
        val mClient: OkHttpClient = OkHttpClient.Builder()
                .readTimeout(15, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS)
                .build()

        val retroit: Retrofit = Retrofit.Builder()
                .baseUrl(MMHealthCareConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .client(mClient)
                .build()

        mTheApi = retroit.create(MMHealthCareApi::class.java)
    }

    override fun loadHealthCareInfo(accessToken: String) {
        var getHealthInfoApiCall: Call<GetHealthCareResponse> = mTheApi!!.loadHealthCareInfo(accessToken)
        getHealthInfoApiCall.enqueue(object : Callback<GetHealthCareResponse> {

            override fun onResponse(call: Call<GetHealthCareResponse>?, response: Response<GetHealthCareResponse>?) {
                val healthCareResponse: GetHealthCareResponse? = response!!.body()
                if (healthCareResponse != null && healthCareResponse.isResponseOK()) {
                    val successEvent=SuccessGetHealthCareInfoEvent(healthCareResponse.healthCareInfoList!!)
                    EventBus.getDefault().post(successEvent)
                } else {
                    if (healthCareResponse == null) {
                        val errorEvent=ApiErrorEvent("Empty In Response")
                        EventBus.getDefault().post(errorEvent)
                    } else {
                        val errorEvent=ApiErrorEvent(healthCareResponse.message)
                        EventBus.getDefault().post(errorEvent)
                    }
                }
            }

            override fun onFailure(call: Call<GetHealthCareResponse>?, t: Throwable?) {
                val errorEvent=ApiErrorEvent(t!!.message!!)
                EventBus.getDefault().post(errorEvent)
            }
        })
    }
}