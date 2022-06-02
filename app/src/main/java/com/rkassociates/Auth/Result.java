package com.rkassociates.Auth;

import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("id")
    public String id;

    @SerializedName("profile_picture")
    public String profile_picture;

    @SerializedName("name")
    public String name;

    @SerializedName("email")
    public String email;

    @SerializedName("phone_number")
    public String phone_number;

    @SerializedName("reference_number")
    public String refrence_number;

    @SerializedName("address")
    public String address;

    @SerializedName("pincode")
    public String pincode;

    @SerializedName("city")
    public String city;

    @SerializedName("state0")
    public String state;

    @SerializedName("aadhar_number")
    public String aadhar_number;

    @SerializedName("pan_number")
    public String pan_number;

    @SerializedName("user_type")
    public String user_type;

    @SerializedName("password")
    public String password;

    @SerializedName("status")
    public String status;

    @SerializedName("created_date")
    public String created_date;


    // Getter Methods

    public String getId() {
        return id;
    }

    public String getProfile_picture() {
        return profile_picture;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getRefrence_number() {
        return refrence_number;
    }

    public String getAddress() {
        return address;
    }

    public String getPincode() {
        return pincode;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getAadhar_number() {
        return aadhar_number;
    }

    public String getPan_number() {
        return pan_number;
    }

    public String getUser_type() {
        return user_type;
    }

    public String getPassword() {
        return password;
    }

    public String getStatus() {
        return status;
    }

    public String getCreated_date() {
        return created_date;
    }

    // Setter Methods

    public void setId(String id) {
        this.id = id;
    }

    public void setProfile_picture(String profile_picture) {
        this.profile_picture = profile_picture;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public void setRefrence_number(String refrence_number) {
        this.refrence_number = refrence_number;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setAadhar_number(String aadhar_number) {
        this.aadhar_number = aadhar_number;
    }

    public void setPan_number(String pan_number) {
        this.pan_number = pan_number;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }
}
