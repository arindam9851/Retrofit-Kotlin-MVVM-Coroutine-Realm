package com.mvvm_kotlin_retrofit.data.api

import com.mvvm_kotlin_retrofit.data.model.LanguageListResponse

class ApiHelper(private val apiService: ApiService) {

    suspend fun getLanguageList(s: String): LanguageListResponse = apiService.getLanguageList(s)

}