package com.rkassociates.UploadAppliDoc.Pages.TeleVerifPage.ApiCalls.ReadData;

import com.google.gson.annotations.SerializedName;

public class TeleVerificationReferenceTable {
    @SerializedName("tele_verification_id")
    private String teleVerificationId;
    @SerializedName("executive_id")
    private String executiveId;
    @SerializedName("applicant_id")
    private String applicantId;
    @SerializedName("refrence_name")
    private String refrenceName;
    @SerializedName("phone_number")
    private String phoneNumber;
    @SerializedName("telecalling_by")
    private String telecallingBy;
    @SerializedName("conversation")
    private String conversation;
    @SerializedName("refrence_name1")
    private String refrenceName1;
    @SerializedName("phone_number1")
    private String phoneNumber1;
    @SerializedName("telecalling_by1")
    private String telecallingBy1;
    @SerializedName("date_time")
    private String dateTime;
    @SerializedName("conversation1")
    private String conversation1;
    @SerializedName("tele_verification_status")
    private String teleVerificationStatus;
    @SerializedName("created_date")
    private String createdDate;

    public String getTeleVerificationId() {
        return teleVerificationId;
    }

    public void setTeleVerificationId(String teleVerificationId) {
        this.teleVerificationId = teleVerificationId;
    }

    public String getExecutiveId() {
        return executiveId;
    }

    public void setExecutiveId(String executiveId) {
        this.executiveId = executiveId;
    }

    public String getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(String applicantId) {
        this.applicantId = applicantId;
    }

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

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getConversation1() {
        return conversation1;
    }

    public void setConversation1(String conversation1) {
        this.conversation1 = conversation1;
    }

    public String getTeleVerificationStatus() {
        return teleVerificationStatus;
    }

    public void setTeleVerificationStatus(String teleVerificationStatus) {
        this.teleVerificationStatus = teleVerificationStatus;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
}