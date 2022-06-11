package com.rkassociates.UploadAppliDoc.Pages.AssetsVerifPage.ApiCalls.ReadData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AssetsReadResponse {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private AssetsReadData2 data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public AssetsReadData2 getData() {
        return data;
    }

    public void setData(AssetsReadData2 data) {
        this.data = data;
    }
}
