package com.rkassociates.UploadAppliDoc.Pages.RegisteredPage.APICall;

import com.google.gson.annotations.SerializedName;

public class Result {
    @SerializedName("applicant_name")
    public String applicant_name;
    @SerializedName("residence_verification")
    public String residence_verification;
    @SerializedName("business_verification")
    public String business_verification;
    @SerializedName("residence")
    public String residence;
    @SerializedName("work")
    public String work;
    @SerializedName("iTR_verification")
    public String iTR_verification;
    @SerializedName("tDS_certificate_verification")
    public String tDS_certificate_verification;
    @SerializedName("bank_statement_verification")
    public String bank_statement_verification;
    @SerializedName("change_property_verification")
    public String change_property_verification;
    @SerializedName("pan_number")
    public String pan_number;
    @SerializedName("aadhaar_number")
    public String aadhaar_number;
    @SerializedName("electricity_bill")
    public String electricity_bill;
    @SerializedName("driving_license")
    public String driving_license;
    @SerializedName("_0")
    public int _0;

    public String getApplicant_name() {
        return applicant_name;
    }

    public void setApplicant_name(String applicant_name) {
        this.applicant_name = applicant_name;
    }

    public String getResidence_verification() {
        return residence_verification;
    }

    public void setResidence_verification(String residence_verification) {
        this.residence_verification = residence_verification;
    }

    public String getBusiness_verification() {
        return business_verification;
    }

    public void setBusiness_verification(String business_verification) {
        this.business_verification = business_verification;
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

    public String getiTR_verification() {
        return iTR_verification;
    }

    public void setiTR_verification(String iTR_verification) {
        this.iTR_verification = iTR_verification;
    }

    public String gettDS_certificate_verification() {
        return tDS_certificate_verification;
    }

    public void settDS_certificate_verification(String tDS_certificate_verification) {
        this.tDS_certificate_verification = tDS_certificate_verification;
    }

    public String getBank_statement_verification() {
        return bank_statement_verification;
    }

    public void setBank_statement_verification(String bank_statement_verification) {
        this.bank_statement_verification = bank_statement_verification;
    }

    public String getChange_property_verification() {
        return change_property_verification;
    }

    public void setChange_property_verification(String change_property_verification) {
        this.change_property_verification = change_property_verification;
    }

    public String getPan_number() {
        return pan_number;
    }

    public void setPan_number(String pan_number) {
        this.pan_number = pan_number;
    }

    public String getAadhaar_number() {
        return aadhaar_number;
    }

    public void setAadhaar_number(String aadhaar_number) {
        this.aadhaar_number = aadhaar_number;
    }

    public String getElectricity_bill() {
        return electricity_bill;
    }

    public void setElectricity_bill(String electricity_bill) {
        this.electricity_bill = electricity_bill;
    }

    public String getDriving_license() {
        return driving_license;
    }

    public void setDriving_license(String driving_license) {
        this.driving_license = driving_license;
    }

    public int get_0() {
        return _0;
    }

    public void set_0(int _0) {
        this._0 = _0;
    }
}
