package com.rkassociates.UploadAppliDoc.Pages.RegisteredPage.APICall.ReadData;

import com.google.gson.annotations.SerializedName;

public class CoApplicantData {

    @SerializedName("co_applicant_id")
    public String coApplicantId;
    @SerializedName("add_data_id")
    public String addDataId;
    @SerializedName("co_applicant_name")
    public String coApplicantName;
    @SerializedName("residence_verification")
    public String residenceVerification;
    @SerializedName("business_verification")
    public String businessVerification;
    @SerializedName("residence")
    public String residence;
    @SerializedName("work")
    public String work;
    @SerializedName("ITR_verification")
    public String iTRVerification;
    @SerializedName("TDS_certificate_verification")
    public String tDSCertificateVerification;
    @SerializedName("bank_statement_verification")
    public String bankStatementVerification;
    @SerializedName("change_property_verification")
    public String changePropertyVerification;
    @SerializedName("pan_number")
    public String panNumber;
    @SerializedName("aadhaar_number")
    public String aadhaarNumber;
    @SerializedName("electricity_bill")
    public String electricityBill;
    @SerializedName("driving_license")
    public String drivingLicense;
    @SerializedName("co_applicant_status")
    public String coApplicantStatus;
    @SerializedName("created_date")
    public String createdDate;

    public String getCoApplicantId() {
        return coApplicantId;
    }

    public void setCoApplicantId(String coApplicantId) {
        this.coApplicantId = coApplicantId;
    }

    public String getAddDataId() {
        return addDataId;
    }

    public void setAddDataId(String addDataId) {
        this.addDataId = addDataId;
    }

    public String getCoApplicantName() {
        return coApplicantName;
    }

    public void setCoApplicantName(String coApplicantName) {
        this.coApplicantName = coApplicantName;
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

    public String getCoApplicantStatus() {
        return coApplicantStatus;
    }

    public void setCoApplicantStatus(String coApplicantStatus) {
        this.coApplicantStatus = coApplicantStatus;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
}
