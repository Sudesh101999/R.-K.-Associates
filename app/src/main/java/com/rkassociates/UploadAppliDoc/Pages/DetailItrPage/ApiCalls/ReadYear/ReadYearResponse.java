package com.rkassociates.UploadAppliDoc.Pages.DetailItrPage.ApiCalls.ReadYear;

import com.google.gson.annotations.SerializedName;

public class ReadYearResponse {
    @SerializedName("status")
    private String status;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private ReadYearData data;

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

    public ReadYearData getData() {
        return data;
    }

    public void setData(ReadYearData data) {
        this.data = data;
    }
}
