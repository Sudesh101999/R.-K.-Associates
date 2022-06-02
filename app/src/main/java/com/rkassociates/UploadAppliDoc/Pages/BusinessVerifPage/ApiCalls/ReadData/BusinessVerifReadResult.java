package com.rkassociates.UploadAppliDoc.Pages.BusinessVerifPage.ApiCalls.ReadData;

import com.google.gson.annotations.SerializedName;

public class BusinessVerifReadResult {
    @SerializedName("business_verification_table")
    private BusinessVerificationTable businessVerificationTable;

    public BusinessVerificationTable getBusinessVerificationTable() {
        return businessVerificationTable;
    }

    public void setBusinessVerificationTable(BusinessVerificationTable businessVerificationTable) {
        this.businessVerificationTable = businessVerificationTable;
    }
}
