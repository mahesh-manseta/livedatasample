package com.octabeans.retrosample.data

import com.google.gson.annotations.SerializedName

class ResVendor: ResBase() {
    @SerializedName("data")
    var vendorResponseData: ResVendorData? = null
}