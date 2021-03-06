package com.rkassociates.UploadAppliDoc.Pages.AssetsVerifPage.ApiCalls;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AssetsVerifResult {
    @SerializedName("executive_id")
    @Expose
    private String executiveId;
    @SerializedName("add_data_id")
    @Expose
    private String addDataId;
    @SerializedName("person_met")
    @Expose
    private String personMet;
    @SerializedName("phone_number")
    @Expose
    private String phoneNumber;
    @SerializedName("doc_of_ownership")
    @Expose
    private String docOfOwnership;
    @SerializedName("margin_money_payment")
    @Expose
    private String marginMoneyPayment;
    @SerializedName("mode_of_payment")
    @Expose
    private String modeOfPayment;
    @SerializedName("seller_acc_verified")
    @Expose
    private String sellerAccVerified;
    @SerializedName("bank_charge")
    @Expose
    private String bankCharge;
    @SerializedName("NOC_issue")
    @Expose
    private String nOCIssue;
    @SerializedName("agreement_value")
    @Expose
    private String agreementValue;
    @SerializedName("stamp_duty")
    @Expose
    private String stampDuty;
    @SerializedName("registration")
    @Expose
    private String registration;
    @SerializedName("index")
    @Expose
    private String index;
    @SerializedName("pancard_number")
    @Expose
    private String pancardNumber;
    @SerializedName("product_type")
    @Expose
    private String productType;
    @SerializedName("v_date")
    @Expose
    private String vDate;
    @SerializedName("v_time")
    @Expose
    private String vTime;
    @SerializedName("assets_verification_status")
    @Expose
    private Integer assetsVerificationStatus;
    @SerializedName("product_type_details")
    @Expose
    private String productTypeDetails;

    public String getExecutiveId() {
        return executiveId;
    }

    public void setExecutiveId(String executiveId) {
        this.executiveId = executiveId;
    }

    public String getAddDataId() {
        return addDataId;
    }

    public void setAddDataId(String addDataId) {
        this.addDataId = addDataId;
    }

    public String getPersonMet() {
        return personMet;
    }

    public void setPersonMet(String personMet) {
        this.personMet = personMet;
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

    public String getModeOfPayment() {
        return modeOfPayment;
    }

    public void setModeOfPayment(String modeOfPayment) {
        this.modeOfPayment = modeOfPayment;
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

    public String getvDate() {
        return vDate;
    }

    public void setvDate(String vDate) {
        this.vDate = vDate;
    }

    public String getvTime() {
        return vTime;
    }

    public void setvTime(String vTime) {
        this.vTime = vTime;
    }

    public Integer getAssetsVerificationStatus() {
        return assetsVerificationStatus;
    }

    public void setAssetsVerificationStatus(Integer assetsVerificationStatus) {
        this.assetsVerificationStatus = assetsVerificationStatus;
    }

    public String getProductTypeDetails() {
        return productTypeDetails;
    }

    public void setProductTypeDetails(String productTypeDetails) {
        this.productTypeDetails = productTypeDetails;
    }
}
