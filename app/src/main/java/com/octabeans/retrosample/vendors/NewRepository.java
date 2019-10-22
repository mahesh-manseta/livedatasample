package com.octabeans.retrosample.vendors;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.octabeans.retrosample.data.ResVendor;
import com.octabeans.retrosample.retrofit.ApiClient;
import com.octabeans.retrosample.retrofit.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class NewRepository {

    private static NewRepository newsRepository;

    static NewRepository getInstance(Context mContext) {
        if (newsRepository == null) {
            newsRepository = new NewRepository(mContext);
        }
        return newsRepository;
    }

    private ApiService newsApi;

    private NewRepository(Context mContext) {
        newsApi = ApiClient.getClient(mContext).create(ApiService.class);
    }

    MutableLiveData<ResVendor> getData() {
        MutableLiveData<ResVendor> newsData = new MutableLiveData<>();
        newsApi.fetchAllVendors().enqueue(new Callback<ResVendor>() {
            @Override
            public void onResponse(Call<ResVendor> call, Response<ResVendor> response) {
                if (response.isSuccessful()) {
                    newsData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ResVendor> call, Throwable t) {
                newsData.setValue(null);
            }
        });
        return newsData;
    }
}
