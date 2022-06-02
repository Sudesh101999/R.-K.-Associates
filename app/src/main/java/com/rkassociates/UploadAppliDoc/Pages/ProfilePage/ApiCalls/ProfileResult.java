package com.rkassociates.UploadAppliDoc.Pages.ProfilePage.ApiCalls;

import com.google.gson.annotations.SerializedName;

public class ProfileResult {

    @SerializedName("add_data_id")
    private String add_data_id;
    @SerializedName("executiveId")
    private String executiveId;
    @SerializedName("submissionDate")
    private String submissionDate;
    @SerializedName("residence")
    private String residence;
    @SerializedName("durationOfStay")
    private String durationOfStay;
    @SerializedName("qulification")
    private String qulification;
    @SerializedName("businessServices")
    private String businessServices;
    @SerializedName("employeeName")
    private String employeeName;
    @SerializedName("designation")
    private String designation;
    @SerializedName("commencementOfBusiness")
    private String commencementOfBusiness;
    @SerializedName("profileRemarkStatus")
    private int profileRemarkStatus;

    public String getAdd_data_id() {
        return add_data_id;
    }

    public void setAdd_data_id(String add_data_id) {
        this.add_data_id = add_data_id;
    }

    public String getExecutiveId() {
        return executiveId;
    }

    public void setExecutiveId(String executiveId) {
        this.executiveId = executiveId;
    }

    public String getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(String submissionDate) {
        this.submissionDate = submissionDate;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    public String getDurationOfStay() {
        return durationOfStay;
    }

    public void setDurationOfStay(String durationOfStay) {
        this.durationOfStay = durationOfStay;
    }

    public String getQulification() {
        return qulification;
    }

    public void setQulification(String qulification) {
        this.qulification = qulification;
    }

    public String getBusinessServices() {
        return businessServices;
    }

    public void setBusinessServices(String businessServices) {
        this.businessServices = businessServices;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getCommencementOfBusiness() {
        return commencementOfBusiness;
    }

    public void setCommencementOfBusiness(String commencementOfBusiness) {
        this.commencementOfBusiness = commencementOfBusiness;
    }

    public int getProfileRemarkStatus() {
        return profileRemarkStatus;
    }

    public void setProfileRemarkStatus(int profileRemarkStatus) {
        this.profileRemarkStatus = profileRemarkStatus;
    }
}
