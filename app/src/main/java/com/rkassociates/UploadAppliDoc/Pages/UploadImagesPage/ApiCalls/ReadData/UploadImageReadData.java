package com.rkassociates.UploadAppliDoc.Pages.UploadImagesPage.ApiCalls.ReadData;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class UploadImageReadData {
    @SerializedName("upload_image_table")
    private List<UploadImageTable> uploadImageTable = new ArrayList<UploadImageTable>();

    public List<UploadImageTable> getUploadImageTable() {
        return uploadImageTable;
    }

    public void setUploadImageTable(List<UploadImageTable> uploadImageTable) {
        this.uploadImageTable = uploadImageTable;
    }
}
