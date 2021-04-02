package com.mvvm_kotlin_retrofit.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mvvm_kotlin_retrofit.data.api.ApiHelper
import com.mvvm_kotlin_retrofit.data.repository.MainRepository
import com.mvvm_kotlin_retrofit.ui.main.view.MainActivity
import com.mvvm_kotlin_retrofit.ui.main.viewmodel.MainViewModel


class ViewModelFactory(private val apiHelper: ApiHelper, val mContext: MainActivity
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(MainRepository(apiHelper),mContext) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}

