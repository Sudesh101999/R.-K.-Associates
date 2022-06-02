package com.rkassociates.UploadAppliDoc.Pages.CurrentResidenceVerifPage.ApiCalls.ReadData;

import com.google.gson.annotations.SerializedName;

public class CurrentResidenceVerificationTable {
    @SerializedName("residence_verification_id")
    private String residenceVerificationId;
    @SerializedName("executive_id")
    private String executiveId;
    @SerializedName("applicant_id")
    private String applicantId;
    @SerializedName("applicant_name")
    private String applicantName;
    @SerializedName("mobile_number")
    private String mobileNumber;
    @SerializedName("email")
    private String email;
    @SerializedName("DOB")
    private String dOB;
    @SerializedName("gender")
    private String gender;
    @SerializedName("qualification")
    private String qualification;
    @SerializedName("marital_status")
    private String maritalStatus;
    @SerializedName("adharcard_number")
    private String adharcardNumber;
    @SerializedName("utility_bill")
    private String utilityBill;
    @SerializedName("application_relation")
    private String applicationRelation;
    @SerializedName("family_mambers")
    private String familyMambers;
    @SerializedName("earning_members")
    private String earningMembers;
    @SerializedName("children")
    private String children;
    @SerializedName("current_address")
    private String currentAddress;
    @SerializedName("residence_verification_status")
    private String residenceVerificationStatus;
    @SerializedName("created_date")
    private String createdDate;

    public String getResidenceVerificationId() {
        return residenceVerificationId;
    }

    public void setResidenceVerificationId(String residenceVerificationId) {
        this.residenceVerificationId = residenceVerificationId;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
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

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getdOB() {
        return dOB;
    }

    public void setdOB(String dOB) {
        this.dOB = dOB;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getAdharcardNumber() {
        return adharcardNumber;
    }

    public void setAdharcardNumber(String adharcardNumber) {
        this.adharcardNumber = adharcardNumber;
    }

    public String getUtilityBill() {
        return utilityBill;
    }

    public void setUtilityBill(String utilityBill) {
        this.utilityBill = utilityBill;
    }

    public String getApplicationRelation() {
        return applicationRelation;
    }

    public void setApplicationRelation(String applicationRelation) {
        this.applicationRelation = applicationRelation;
    }

    public String getFamilyMambers() {
        return familyMambers;
    }

    public void setFamilyMambers(String familyMambers) {
        this.familyMambers = familyMambers;
    }

    public String getEarningMembers() {
        return earningMembers;
    }

    public void setEarningMembers(String earningMembers) {
        this.earningMembers = earningMembers;
    }

    public String getChildren() {
        return children;
    }

    public void setChildren(String children) {
        this.children = children;
    }

    public String getCurrentAddress() {
        return currentAddress;
    }

    public void setCurrentAddress(String currentAddress) {
        this.currentAddress = currentAddress;
    }

    public String getResidenceVerificationStatus() {
        return residenceVerificationStatus;
    }

    public void setResidenceVerificationStatus(String residenceVerificationStatus) {
        this.residenceVerificationStatus = residenceVerificationStatus;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
}
