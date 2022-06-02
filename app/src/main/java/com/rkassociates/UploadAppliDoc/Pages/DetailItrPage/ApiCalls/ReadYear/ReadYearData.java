package com.rkassociates.UploadAppliDoc.Pages.DetailItrPage.ApiCalls.ReadYear;

import com.google.gson.annotations.SerializedName;

public class ReadYearData {
    @SerializedName("itr_kyc_verification_table")
    private itr_kyc_verification_table_year itr_kyc_verification_table_year;

    public com.rkassociates.UploadAppliDoc.Pages.DetailItrPage.ApiCalls.ReadYear.itr_kyc_verification_table_year getItr_kyc_verification_table_year() {
        return itr_kyc_verification_table_year;
    }

    public void setItr_kyc_verification_table_year(com.rkassociates.UploadAppliDoc.Pages.DetailItrPage.ApiCalls.ReadYear.itr_kyc_verification_table_year itr_kyc_verification_table_year) {
        this.itr_kyc_verification_table_year = itr_kyc_verification_table_year;
    }
}
