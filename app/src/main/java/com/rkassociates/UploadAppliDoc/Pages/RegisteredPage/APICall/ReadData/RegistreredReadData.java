package com.rkassociates.UploadAppliDoc.Pages.RegisteredPage.APICall.ReadData;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RegistreredReadData {

    @SerializedName("applicant")
    private Applicant applicant;
    @SerializedName("co_applicant_table")
    private List<Applicant> coApplicant;

    public Applicant getApplicant() {
        return applicant;
    }

    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }

    public List<Applicant> getCoApplicant() {
        return coApplicant;
    }

    public void setCoApplicant(List<Applicant> coApplicant) {
        this.coApplicant = coApplicant;
    }
}
