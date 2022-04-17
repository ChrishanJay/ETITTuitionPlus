package com.etit.smartpay.model;

import java.util.ArrayList;

public class TuitionClass {

    private String className;
    private String tutor;
    private String location;
    private String time;
    private String classFee;
    private String image;
    private boolean online;

    public TuitionClass(String className, String tutor, String location, String time, String classFee, String image, boolean online) {
        this.className = className;
        this.tutor = tutor;
        this.location = location;
        this.time = time;
        this.classFee = classFee;
        this.image = image;
        this.online = online;
    }

    public String getClassName() {
        return className;
    }

    public String getTutor() {
        return tutor;
    }

    public String getLocation() {
        return location;
    }

    public String getTime() {
        return time;
    }

    public String getClassFee() {
        return classFee;
    }

    public String getImage() {
        return image;
    }

    public boolean isOnline() {
        return online;
    }
}
