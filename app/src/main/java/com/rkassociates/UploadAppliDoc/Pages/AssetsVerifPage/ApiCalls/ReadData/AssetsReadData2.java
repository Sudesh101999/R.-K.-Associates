package com.rkassociates.UploadAppliDoc.Pages.AssetsVerifPage.ApiCalls.ReadData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AssetsReadData2 {

    @SerializedName("assets_verification_table")
    @Expose
    private AssetsReadData assetsData;

    public AssetsReadData getAssetsData() {
        return assetsData;
    }

    public void setAssetsData(AssetsReadData assetsData) {
        this.assetsData = assetsData;
    }
}
