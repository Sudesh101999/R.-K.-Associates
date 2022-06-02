package com.rkassociates.UploadAppliDoc.Pages.CurrentResidPropertyPage.APiCalls.ReadData;

import com.google.gson.annotations.SerializedName;

public class CRPReadResult {

    @SerializedName("current_residence_property_table")
    private CurrentResidencePropertyTable currentResidencePropertyTable;

    public CurrentResidencePropertyTable getCurrentResidencePropertyTable() {
        return currentResidencePropertyTable;
    }

    public void setCurrentResidencePropertyTable(CurrentResidencePropertyTable currentResidencePropertyTable) {
        this.currentResidencePropertyTable = currentResidencePropertyTable;
    }
}
