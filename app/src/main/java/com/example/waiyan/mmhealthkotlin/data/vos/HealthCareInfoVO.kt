package com.example.waiyan.mmhealthkotlin.data.vos

import com.google.gson.annotations.SerializedName

class HealthCareInfoVO {

    @SerializedName("id")
    val id: Int = 0

    @SerializedName("title")
    val title: String = ""

    @SerializedName("image")
    val image: String = ""

    @SerializedName("author")
    val authorVO: AuthorVO?=null

    @SerializedName("short-description")
    val shortDescription: String = ""

    @SerializedName("published-date")
    val publishedDate: String = ""

    @SerializedName("complete-url")
    val completedUrl:String = ""

    @SerializedName("info-type")
    val infoType: String = ""

}