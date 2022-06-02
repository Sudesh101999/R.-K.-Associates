package com.rkassociates.UploadAppliDoc.Pages.DetailItrPage.ApiCalls.ReadData;

import com.google.gson.annotations.SerializedName;

public class DetailsItrReadResult {

    @SerializedName("details_of_itr_table")
    private DetailsOfItrTable detailsOfItrTable;

    public DetailsOfItrTable getDetailsOfItrTable() {
        return detailsOfItrTable;
    }

    public void setDetailsOfItrTable(DetailsOfItrTable detailsOfItrTable) {
        this.detailsOfItrTable = detailsOfItrTable;
    }
}
