package com.rkassociates.DocPicker.ApiCalling;

import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("executive_id")
    public String executiveId;
    @SerializedName("bank_name")
    public String bankName;
    @SerializedName("branch_name")
    public String branchName;
    @SerializedName("reference_name")
    public String referenceName;
    @SerializedName("applicant_name")
    public String applicantName;
    @SerializedName("co_applicant_name")
    public String coApplicantName;
    @SerializedName("loan_amount")
    public String loanAmount;
    @SerializedName("product_type")
    public String productType;
    @SerializedName("submit_date")
    public String submitDate;
    @SerializedName("add_data_status")
    public int addDataStatus;

    public String getExecutiveId() {
        return executiveId;
    }

    public void setExecutiveId(String executiveId) {
        this.executiveId = executiveId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getReferenceName() {
        return referenceName;
    }

    public void setReferenceName(String referenceName) {
        this.referenceName = referenceName;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public String getCoApplicantName() {
        return coApplicantName;
    }

    public void setCoApplicantName(String coApplicantName) {
        this.coApplicantName = coApplicantName;
    }

    public String getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(String loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(String submitDate) {
        this.submitDate = submitDate;
    }

    public int getAddDataStatus() {
        return addDataStatus;
    }

    public void setAddDataStatus(int addDataStatus) {
        this.addDataStatus = addDataStatus;
    }
}
