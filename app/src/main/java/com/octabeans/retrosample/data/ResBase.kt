package com.octabeans.retrosample.data

import com.google.gson.annotations.SerializedName

open class ResBase {
    @SerializedName("success")
    var success: String = ""
    @SerializedName("error")
    var error: String = ""
}