package com.etit.smartpay.network;

import com.google.gson.annotations.SerializedName;

public class EmailRequest {
//    "name":"chathra",
//    "guardian_email_address":"chrishan1989@gmail.com",
//    "class_name":"AL Maths"

    @SerializedName("name")
    private String name;

    @SerializedName("guardian_email_address")
    private String email;

    @SerializedName("class_name")
    private String className;

    public EmailRequest(String name, String email, String className) {
        this.name = name;
        this.email = email;
        this.className = className;
    }
}
