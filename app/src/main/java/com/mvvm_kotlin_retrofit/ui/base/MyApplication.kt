package com.mvvm_kotlin_retrofit.ui.base

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration


class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val config = RealmConfiguration.Builder()
            .name("myrealm.realm")
//            .encryptionKey(getKey())
            .build()
        Realm.setDefaultConfiguration(config)
    }
}