package com.rkassociates.UploadAppliDoc.Pages.DetailItrPage.ApiCalls.ReadYear;

import com.google.gson.annotations.SerializedName;

public class itr_kyc_verification_table_year {
    @SerializedName("year1")
    private String year1;
    @SerializedName("year2")
    private String year2;
    @SerializedName("year3")
    private String year3;
    @SerializedName("applicant_name")
    private String applicantName;

    public String getYear1() {
        return year1;
    }

    public void setYear1(String year1) {
        this.year1 = year1;
    }

    public String getYear2() {
        return year2;
    }

    public void setYear2(String year2) {
        this.year2 = year2;
    }

    public String getYear3() {
        return year3;
    }

    public void setYear3(String year3) {
        this.year3 = year3;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }
}
