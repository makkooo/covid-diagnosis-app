package com.covidapp;

import org.parceler.Parcel;

@Parcel
public class User {

    String gender, ageGroup, region;

    public User() {}

    public User(String gender, String ageGroup, String region){
        this.gender = gender;
        this.ageGroup = ageGroup;
        this.region = region;
    }
}
