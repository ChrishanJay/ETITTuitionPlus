package com.etit.smartpay.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ETITRetrofit {

    public static String BASE_URL = "https://yhn5jz8d70.execute-api.us-east-1.amazonaws.com/"; //dev/send_sms
    private static Retrofit retrofit;

    public static Retrofit getRetrofit() {
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
