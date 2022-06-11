package com.rkassociates.UploadAppliDoc.Pages.RegisteredPage.APICall.ReadData;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class registeredReadData {
    @SerializedName("applicant")
    public ApplicantData applicant;

    @SerializedName("co_applicant_table")
    public List<CoApplicantData> coApplicantTable = null;

    public ApplicantData getApplicant() {
        return applicant;
    }

    public void setApplicant(ApplicantData applicant) {
        this.applicant = applicant;
    }

    public List<CoApplicantData> getCoApplicantTable() {
        return coApplicantTable;
    }

    public void setCoApplicantTable(List<CoApplicantData> coApplicantTable) {
        this.coApplicantTable = coApplicantTable;
    }

}
