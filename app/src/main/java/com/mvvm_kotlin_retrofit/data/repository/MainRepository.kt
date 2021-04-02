package com.mvvm_kotlin_retrofit.data.repository

import com.mvvm_kotlin_retrofit.data.api.ApiHelper
import com.mvvm_kotlin_retrofit.data.model.LanguageListResponse


class MainRepository(private val apiHelper: ApiHelper) {
    suspend fun getLanguageList(s: String): LanguageListResponse = apiHelper.getLanguageList(s)

}