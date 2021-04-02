package com.mvvm_kotlin_retrofit.data.model


import com.google.gson.annotations.SerializedName

data class LanguageListResponse(
    @SerializedName("data")
    var `data`: List<Data> = listOf(),
    @SerializedName("success")
    var success: Boolean = false,
    @SerializedName("totalCount")
    var totalCount: Int = 0
)