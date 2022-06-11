package com.rkassociates.UploadAppliDoc.Pages.DetailItrPage.ApiCalls.ReadData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DetailsItrReadResult {

    @SerializedName("details_of_itr_table")
    @Expose
    private List<DetailsOfItrTable> detailsOfItrTable;

    public List<DetailsOfItrTable> getDetailsOfItrTable() {
        return detailsOfItrTable;
    }

    public void setDetailsOfItrTable(List<DetailsOfItrTable> detailsOfItrTable) {
        this.detailsOfItrTable = detailsOfItrTable;
    }
}
