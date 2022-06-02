package com.rkassociates.UploadAppliDoc.Pages.TeleVerifPage.ApiCalls.ReadData;

import com.google.gson.annotations.SerializedName;

public class TeleVerifResponse {

    @SerializedName("status")
    private String status;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private TeleVerifData data;

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

    public TeleVerifData getData() {
        return data;
    }

    public void setData(TeleVerifData data) {
        this.data = data;
    }
}
