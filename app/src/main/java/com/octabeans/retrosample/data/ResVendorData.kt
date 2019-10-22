package com.octabeans.retrosample.data

import com.google.gson.annotations.SerializedName
import java.util.ArrayList

class ResVendorData {
    @SerializedName("current_page")
    var current_page: Int = 0

    @SerializedName("last_page")
    var last_page: Int = 0

    @SerializedName("data")
    var vendors: ArrayList<ResNote> = ArrayList()
}