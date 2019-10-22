package com.octabeans.retrosample.data

import com.google.gson.annotations.SerializedName

class ResNote {
    @SerializedName("id")
    var id: Int = 0
    @SerializedName("name")
    var note: String = ""
    @SerializedName("created_at")
    var timestamp: String = ""
}