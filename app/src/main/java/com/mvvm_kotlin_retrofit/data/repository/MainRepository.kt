package com.mvvm_kotlin_retrofit.data.repository

import com.mvvm_kotlin_retrofit.data.api.ApiHelper
import com.mvvm_kotlin_retrofit.data.model.Data
import com.mvvm_kotlin_retrofit.data.model.LanguageListResponse
import com.mvvm_kotlin_retrofit.db.RealmLiveData
import io.realm.Realm


class MainRepository(private val apiHelper: ApiHelper) {
    suspend fun getLanguageList(s: String): LanguageListResponse = apiHelper.getLanguageList(s)

}