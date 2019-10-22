package com.octabeans.retrosample.retrofit;

import com.google.gson.annotations.SerializedName;

public class BaseResponse {

    @SerializedName("success")
    String success;
    @SerializedName("error")
    String error;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
