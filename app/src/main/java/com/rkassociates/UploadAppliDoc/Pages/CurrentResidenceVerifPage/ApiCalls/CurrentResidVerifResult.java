package com.rkassociates.UploadAppliDoc.Pages.CurrentResidenceVerifPage.ApiCalls;

import com.google.gson.annotations.SerializedName;

public class CurrentResidVerifResult {

    @SerializedName("executiveId")
    private String executiveId;
    @SerializedName("applicantId")
    private String applicantId;
    @SerializedName("mobileNumber")
    private String mobileNumber;
    @SerializedName("email")
    private String email;
    @SerializedName("dOB")
    private String dOB;
    @SerializedName("gender")
    private String gender;
    @SerializedName("qualification")
    private String qualification;
    @SerializedName("maritalStatus")
    private String maritalStatus;
    @SerializedName("adharcardNumber")
    private String adharcardNumber;
    @SerializedName("utilityBill")
    private String utilityBill;
    @SerializedName("applicationRelation")
    private String applicationRelation;
    @SerializedName("familyMambers")
    private String familyMambers;
    @SerializedName("earningMembers")
    private String earningMembers;
    @SerializedName("children")
    private String children;
    @SerializedName("currentAddress")
    private String currentAddress;
    @SerializedName("residenceVerificationStatus")
    public int residenceVerificationStatus;

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

    public int getResidenceVerificationStatus() {
        return residenceVerificationStatus;
    }

    public void setResidenceVerificationStatus(int residenceVerificationStatus) {
        this.residenceVerificationStatus = residenceVerificationStatus;
    }
}
