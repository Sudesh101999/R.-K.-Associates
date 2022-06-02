package com.rkassociates.UploadAppliDoc.Pages.RegisteredPage.APICall.ReadData;

import com.google.gson.annotations.SerializedName;

public class Applicant {
    @SerializedName("applicant_id")
    private String applicantId;
    @SerializedName("add_data_id")
    private String addDataId;
    @SerializedName("executive_id")
    private String executiveId;
    @SerializedName("applicant_name")
    private String applicantName;
    @SerializedName("residence_verification")
    private String residenceVerification;
    @SerializedName("business_verification")
    private String businessVerification;
    @SerializedName("residence")
    private String residence;
    @SerializedName("work")
    private String work;
    @SerializedName("ITR_verification")
    private String iTRVerification;
    @SerializedName("TDS_certificate_verification")
    private String tDSCertificateVerification;
    @SerializedName("bank_statement_verification")
    private String bankStatementVerification;
    @SerializedName("change_property_verification")
    private String changePropertyVerification;
    @SerializedName("pan_number")
    private String panNumber;
    @SerializedName("aadhaar_number")
    private String aadhaarNumber;
    @SerializedName("electricity_bill")
    private String electricityBill;
    @SerializedName("driving_license")
    private String drivingLicense;
    @SerializedName("applicant_status")
    private String applicantStatus;
    @SerializedName("created_date")
    private String createdDate;

    public String getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(String applicantId) {
        this.applicantId = applicantId;
    }

    public String getAddDataId() {
        return addDataId;
    }

    public void setAddDataId(String addDataId) {
        this.addDataId = addDataId;
    }

    public String getExecutiveId() {
        return executiveId;
    }

    public void setExecutiveId(String executiveId) {
        this.executiveId = executiveId;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public String getResidenceVerification() {
        return residenceVerification;
    }

    public void setResidenceVerification(String residenceVerification) {
        this.residenceVerification = residenceVerification;
    }

    public String getBusinessVerification() {
        return businessVerification;
    }

    public void setBusinessVerification(String businessVerification) {
        this.businessVerification = businessVerification;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getiTRVerification() {
        return iTRVerification;
    }

    public void setiTRVerification(String iTRVerification) {
        this.iTRVerification = iTRVerification;
    }

    public String gettDSCertificateVerification() {
        return tDSCertificateVerification;
    }

    public void settDSCertificateVerification(String tDSCertificateVerification) {
        this.tDSCertificateVerification = tDSCertificateVerification;
    }

    public String getBankStatementVerification() {
        return bankStatementVerification;
    }

    public void setBankStatementVerification(String bankStatementVerification) {
        this.bankStatementVerification = bankStatementVerification;
    }

    public String getChangePropertyVerification() {
        return changePropertyVerification;
    }

    public void setChangePropertyVerification(String changePropertyVerification) {
        this.changePropertyVerification = changePropertyVerification;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    public String getAadhaarNumber() {
        return aadhaarNumber;
    }

    public void setAadhaarNumber(String aadhaarNumber) {
        this.aadhaarNumber = aadhaarNumber;
    }

    public String getElectricityBill() {
        return electricityBill;
    }

    public void setElectricityBill(String electricityBill) {
        this.electricityBill = electricityBill;
    }

    public String getDrivingLicense() {
        return drivingLicense;
    }

    public void setDrivingLicense(String drivingLicense) {
        this.drivingLicense = drivingLicense;
    }

    public String getApplicantStatus() {
        return applicantStatus;
    }

    public void setApplicantStatus(String applicantStatus) {
        this.applicantStatus = applicantStatus;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
}
