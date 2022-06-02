package com.rkassociates.UploadAppliDoc.Pages.AssetsVerifPage.ApiCalls;

import com.google.gson.annotations.SerializedName;

public class AssetsVerifResult {
    @SerializedName("executiveId")
    private String executiveId;
    @SerializedName("applicantId")
    private String applicantId;
    @SerializedName("phoneNumber")
    private String phoneNumber;
    @SerializedName("docOfOwnership")
    private String docOfOwnership;
    @SerializedName("marginMoneyPayment")
    private String marginMoneyPayment;
    @SerializedName("sellerAccVerified")
    private String sellerAccVerified;
    @SerializedName("bankCharge")
    private String bankCharge;
    @SerializedName("nOCIssue")
    private String nOCIssue;
    @SerializedName("agreementValue")
    private String agreementValue;
    @SerializedName("stampDuty")
    private String stampDuty;
    @SerializedName("registration")
    private String registration;
    @SerializedName("index")
    private String index;
    @SerializedName("pancardNumber")
    private String pancardNumber;
    @SerializedName("productType")
    private String productType;
    @SerializedName("assetsVerificationStatus")
    private int assetsVerificationStatus;

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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDocOfOwnership() {
        return docOfOwnership;
    }

    public void setDocOfOwnership(String docOfOwnership) {
        this.docOfOwnership = docOfOwnership;
    }

    public String getMarginMoneyPayment() {
        return marginMoneyPayment;
    }

    public void setMarginMoneyPayment(String marginMoneyPayment) {
        this.marginMoneyPayment = marginMoneyPayment;
    }

    public String getSellerAccVerified() {
        return sellerAccVerified;
    }

    public void setSellerAccVerified(String sellerAccVerified) {
        this.sellerAccVerified = sellerAccVerified;
    }

    public String getBankCharge() {
        return bankCharge;
    }

    public void setBankCharge(String bankCharge) {
        this.bankCharge = bankCharge;
    }

    public String getnOCIssue() {
        return nOCIssue;
    }

    public void setnOCIssue(String nOCIssue) {
        this.nOCIssue = nOCIssue;
    }

    public String getAgreementValue() {
        return agreementValue;
    }

    public void setAgreementValue(String agreementValue) {
        this.agreementValue = agreementValue;
    }

    public String getStampDuty() {
        return stampDuty;
    }

    public void setStampDuty(String stampDuty) {
        this.stampDuty = stampDuty;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getPancardNumber() {
        return pancardNumber;
    }

    public void setPancardNumber(String pancardNumber) {
        this.pancardNumber = pancardNumber;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public int getAssetsVerificationStatus() {
        return assetsVerificationStatus;
    }

    public void setAssetsVerificationStatus(int assetsVerificationStatus) {
        this.assetsVerificationStatus = assetsVerificationStatus;
    }
}
