package com.rkassociates.UploadAppliDoc.Pages.TeleVerifPage.ApiCalls.ReadData;

import com.google.gson.annotations.SerializedName;

public class TeleVerifData {

    @SerializedName("tele_verification_reference_table")
    private TeleVerificationReferenceTable teleVerificationReferenceTable;

    public TeleVerificationReferenceTable getProfileRemarkTable() {
        return teleVerificationReferenceTable;
    }

    public void setProfileRemarkTable(TeleVerificationReferenceTable teleVerificationReferenceTable) {
        this.teleVerificationReferenceTable = teleVerificationReferenceTable;
    }
}
