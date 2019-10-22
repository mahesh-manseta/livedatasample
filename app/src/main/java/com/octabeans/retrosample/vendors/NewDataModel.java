package com.octabeans.retrosample.vendors;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.octabeans.retrosample.data.ResVendor;


public class NewDataModel extends ViewModel {

    private MutableLiveData<ResVendor> mutableLiveData;
    private NewRepository newsRepository;

    public void init(Context context){
        if (mutableLiveData != null){
            return;
        }
        newsRepository = NewRepository.getInstance(context);
        mutableLiveData = newsRepository.getData();
    }

    public LiveData<ResVendor> getNewsRepository() {
        return mutableLiveData;
    }

}
