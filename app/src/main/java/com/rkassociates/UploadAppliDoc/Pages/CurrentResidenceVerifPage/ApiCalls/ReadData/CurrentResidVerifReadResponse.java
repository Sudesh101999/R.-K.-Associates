package com.rkassociates.UploadAppliDoc.Pages.CurrentResidenceVerifPage.ApiCalls.ReadData;

import com.google.gson.annotations.SerializedName;

public class CurrentResidVerifReadResponse {
    @SerializedName("status")
    private String status;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private CurrentResidVerifReadResult data;

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

    public CurrentResidVerifReadResult getData() {
        return data;
    }

    public void setData(CurrentResidVerifReadResult data) {
        this.data = data;
    }
}
