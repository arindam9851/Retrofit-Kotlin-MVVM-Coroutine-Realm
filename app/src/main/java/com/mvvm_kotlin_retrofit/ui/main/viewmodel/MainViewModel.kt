package com.mvvm_kotlin_retrofit.ui.main.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.mvvm_kotlin_retrofit.data.model.Data
import com.mvvm_kotlin_retrofit.data.model.LanguageListResponse
import com.mvvm_kotlin_retrofit.data.repository.MainRepository
import com.mvvm_kotlin_retrofit.ui.main.view.MainActivity
import com.mvvm_kotlin_retrofit.utils.Resource
import io.realm.Realm

import kotlinx.coroutines.Dispatchers


class MainViewModel(private val mainRepository: MainRepository, val mContext: MainActivity
) : ViewModel() {
    var languageResponse= MutableLiveData<LanguageListResponse>()
    var apiresponse= LanguageListResponse()

    fun getLanguageList(s: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            apiresponse = mainRepository.getLanguageList(s)
            apiresponse.data[4]!!.lang="LiveData Description"
            languageResponse.postValue(apiresponse)
            mContext.runOnUiThread(java.lang.Runnable {
                if(!checkDatabase())
                    saveData(apiresponse)
            })

            emit(Resource.success(data = languageResponse))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    private fun checkDatabase(): Boolean {
        var hasData: Boolean= false
        val mRealm = Realm.getDefaultInstance()
        mRealm!!.executeTransaction { realm ->
            val rows = realm.where(Data::class.java).findAll()
            hasData = rows.size > 0
        }
        return hasData
    }

    private fun saveData(languageResponse: LanguageListResponse) {
        val mRealm = Realm.getDefaultInstance()
        mRealm!!.executeTransactionAsync({ bgRealm ->
            for (i in languageResponse.data!!.indices) {
                val datum = bgRealm.createObject(Data::class.java)
                datum.key=languageResponse.data!![i].key
                datum.code=languageResponse.data!![i].code
                datum.lang=languageResponse.data!![i].lang
                bgRealm.insert(datum)
            }
        },
            {
                Toast.makeText(mContext,"Data insert into Database",Toast.LENGTH_LONG).show()
                Log.d("status---->>>>", "Success")
            })

        { // Transaction failed and was automatically canceled.
            Toast.makeText(mContext,"Failed to data insert into Database",Toast.LENGTH_LONG).show()
            Log.d("status", "failed")
        }

    }
}