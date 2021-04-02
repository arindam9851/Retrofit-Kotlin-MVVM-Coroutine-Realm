package com.mvvm_kotlin_retrofit.data.model


import com.google.gson.annotations.SerializedName
import io.realm.RealmObject

open class Data(
    @SerializedName("code")
    var code: String = "",
    @SerializedName("key")
    var key: String = "",
    @SerializedName("lang")
    var lang: String = ""
):RealmObject()