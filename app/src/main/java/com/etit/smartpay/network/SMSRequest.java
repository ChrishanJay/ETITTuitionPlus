package com.etit.smartpay.network;

import com.google.gson.annotations.SerializedName;

public class SMSRequest {
//    {
//        "name":"chathra",
//        "phone_number":"+94714773127",
//        "class_name":"AL Maths"
//    }
    @SerializedName("name")
    private String name;

    @SerializedName("phone_number")
    private String phoneNo;

    @SerializedName("class_name")
    private String className;

    public SMSRequest(String name, String phoneNo, String className) {
        this.name = name;
        this.phoneNo = phoneNo;
        this.className = className;
    }
}
