package com.example.waiyan.mmhealthkotlin.network.responses

import com.example.waiyan.mmhealthkotlin.data.vos.HealthCareInfoVO
import com.google.gson.annotations.SerializedName

class GetHealthCareResponse {
    @SerializedName("code")
    val code: Int = 0
    @SerializedName("message")
    val message: String = ""
    @SerializedName("healthcare-info")
    val healthCareInfoList: List<HealthCareInfoVO>? = null

    fun isResponseOK(): Boolean {
        return code == 200 && healthCareInfoList != null
    }
}