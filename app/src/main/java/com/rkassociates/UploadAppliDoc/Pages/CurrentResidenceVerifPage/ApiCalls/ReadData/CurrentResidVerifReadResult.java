package com.rkassociates.UploadAppliDoc.Pages.CurrentResidenceVerifPage.ApiCalls.ReadData;

import com.google.gson.annotations.SerializedName;

public class CurrentResidVerifReadResult {
    @SerializedName("current_residence_verification_table")
    private CurrentResidenceVerificationTable currentResidenceVerificationTable;

    public CurrentResidenceVerificationTable getCurrentResidenceVerificationTable() {
        return currentResidenceVerificationTable;
    }

    public void setCurrentResidenceVerificationTable(CurrentResidenceVerificationTable currentResidenceVerificationTable) {
        this.currentResidenceVerificationTable = currentResidenceVerificationTable;
    }
}
