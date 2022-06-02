package com.rkassociates.UploadAppliDoc.Pages.TeleVerifPage.ApiCalls;

import com.google.gson.annotations.SerializedName;

public class Result {
    @SerializedName("refrenceName")
    public String refrenceName;
    @SerializedName("phoneNumber")
    public String phoneNumber;
    @SerializedName("telecallingBy")
    public String telecallingBy;
    @SerializedName("dateTime")
    public String dateTime;
    @SerializedName("conversation")
    public String conversation;
    @SerializedName("refrenceName1")
    public String refrenceName1;
    @SerializedName("phoneNumber1")
    public String phoneNumber1;
    @SerializedName("telecallingBy1")
    public String telecallingBy1;
    @SerializedName("conversation1")
    public String conversation1;
    @SerializedName("teleVerificationStatus")
    public int teleVerificationStatus;

    public String getRefrenceName() {
        return refrenceName;
    }

    public void setRefrenceName(String refrenceName) {
        this.refrenceName = refrenceName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getTelecallingBy() {
        return telecallingBy;
    }

    public void setTelecallingBy(String telecallingBy) {
        this.telecallingBy = telecallingBy;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getConversation() {
        return conversation;
    }

    public void setConversation(String conversation) {
        this.conversation = conversation;
    }

    public String getRefrenceName1() {
        return refrenceName1;
    }

    public void setRefrenceName1(String refrenceName1) {
        this.refrenceName1 = refrenceName1;
    }

    public String getPhoneNumber1() {
        return phoneNumber1;
    }

    public void setPhoneNumber1(String phoneNumber1) {
        this.phoneNumber1 = phoneNumber1;
    }

    public String getTelecallingBy1() {
        return telecallingBy1;
    }

    public void setTelecallingBy1(String telecallingBy1) {
        this.telecallingBy1 = telecallingBy1;
    }

    public String getConversation1() {
        return conversation1;
    }

    public void setConversation1(String conversation1) {
        this.conversation1 = conversation1;
    }

    public int getTeleVerificationStatus() {
        return teleVerificationStatus;
    }

    public void setTeleVerificationStatus(int teleVerificationStatus) {
        this.teleVerificationStatus = teleVerificationStatus;
    }
}
