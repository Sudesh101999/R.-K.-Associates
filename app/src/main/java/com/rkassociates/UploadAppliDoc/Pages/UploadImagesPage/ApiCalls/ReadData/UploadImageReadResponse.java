package com.rkassociates.UploadAppliDoc.Pages.UploadImagesPage.ApiCalls.ReadData;

import com.google.gson.annotations.SerializedName;

public class UploadImageReadResponse {

    @SerializedName("status")
    private String status;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private UploadImageReadData data;

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

    public UploadImageReadData getData() {
        return data;
    }

    public void setData(UploadImageReadData data) {
        this.data = data;
    }
}
