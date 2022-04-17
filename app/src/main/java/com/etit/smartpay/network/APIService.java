package com.etit.smartpay.network;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface APIService {

    @POST("dev/send_sms")
    Call<SMSResponse> sendSMS(@Body SMSRequest smsRequest);

    @POST("dev/send_email")
    Call<EmailResponse> sendEmail(@Body EmailRequest emailRequest);
}
