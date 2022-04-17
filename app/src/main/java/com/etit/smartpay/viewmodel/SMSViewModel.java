package com.etit.smartpay.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.etit.smartpay.network.APIService;
import com.etit.smartpay.network.ETITRetrofit;
import com.etit.smartpay.network.SMSRequest;
import com.etit.smartpay.network.SMSResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SMSViewModel extends ViewModel {

    private MutableLiveData<SMSResponse> smsResponseMutableLiveData;

    public SMSViewModel() {
        smsResponseMutableLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<SMSResponse> getSMSResponseObserver() {
        return smsResponseMutableLiveData;
    }

    public void sendSMS(SMSRequest request) {
        APIService apiService = ETITRetrofit.getRetrofit().create(APIService.class);
        Call<SMSResponse> call = apiService.sendSMS(request);
        call.enqueue(new Callback<SMSResponse>() {
            @Override
            public void onResponse(Call<SMSResponse> call, Response<SMSResponse> response) {
                smsResponseMutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<SMSResponse> call, Throwable t) {
                smsResponseMutableLiveData.postValue(null);
            }
        });
    }
}
