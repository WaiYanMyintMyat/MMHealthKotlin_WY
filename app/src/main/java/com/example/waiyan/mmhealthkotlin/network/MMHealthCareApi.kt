package com.example.waiyan.mmhealthkotlin.network

import com.example.waiyan.mmhealthkotlin.network.responses.GetHealthCareResponse
import com.example.waiyan.mmhealthkotlin.utils.MMHealthCareConstants
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 *
 * Created by Wai Yan on 7/10/18.
 */
interface MMHealthCareApi {

    @FormUrlEncoded
    @POST(MMHealthCareConstants.API_GET_HEALTHCARE_INFO)
    fun loadHealthCareInfo(
            @Field(MMHealthCareConstants.PARAM_ACCESS_TOKEN) accessToken: String
    ): Call<GetHealthCareResponse>
}