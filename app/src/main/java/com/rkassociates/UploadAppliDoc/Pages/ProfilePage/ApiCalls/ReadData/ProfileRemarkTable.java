package com.rkassociates.UploadAppliDoc.Pages.ProfilePage.ApiCalls.ReadData;

import com.google.gson.annotations.SerializedName;

public class ProfileRemarkTable {
    @SerializedName("profile_remark_id")
    private String profileRemarkId;
    @SerializedName("executive_id")
    private String executiveId;
    @SerializedName("applicant_id")
    private String applicantId;
    @SerializedName("submission_date")
    private String submissionDate;
    @SerializedName("applicant_name")
    private String applicantName;
    @SerializedName("residence")
    private String residence;
    @SerializedName("duration_of_stay")
    private String durationOfStay;
    @SerializedName("qulification")
    private String qulification;
    @SerializedName("business_services")
    private String businessServices;
    @SerializedName("employee_name")
    private String employeeName;
    @SerializedName("designation")
    private String designation;
    @SerializedName("commencement_of_business")
    private String commencementOfBusiness;
    @SerializedName("profile_remark_status")
    private String profileRemarkStatus;
    @SerializedName("created_date")
    private String createdDate;

    public String getProfileRemarkId() {
        return profileRemarkId;
    }

    public void setProfileRemarkId(String profileRemarkId) {
        this.profileRemarkId = profileRemarkId;
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

    public String getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(String submissionDate) {
        this.submissionDate = submissionDate;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
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

    public String getProfileRemarkStatus() {
        return profileRemarkStatus;
    }

    public void setProfileRemarkStatus(String profileRemarkStatus) {
        this.profileRemarkStatus = profileRemarkStatus;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
}
