package com.mvvm_kotlin_retrofit.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.realm.RealmChangeListener
import io.realm.RealmModel
import io.realm.RealmResults


class RealmLiveData<T : RealmModel>(private val results: RealmResults<T>) :
        LiveData<RealmResults<T>>()  {
    private val listener: RealmChangeListener<RealmResults<T>> =
            RealmChangeListener { results -> value = results }

    override fun onActive() {
        results.addChangeListener(listener)
    }

    override fun onInactive() {
        results.removeChangeListener(listener)
    }
}

fun <T: RealmModel> RealmResults<T>.asLiveData() = RealmLiveData<T>(this)
