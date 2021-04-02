package com.mvvm_kotlin_retrofit.data.api

import com.mvvm_kotlin_retrofit.data.model.LanguageListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("api/language/get_list")
    suspend fun getLanguageList(@Query("appname")s: String): LanguageListResponse



}