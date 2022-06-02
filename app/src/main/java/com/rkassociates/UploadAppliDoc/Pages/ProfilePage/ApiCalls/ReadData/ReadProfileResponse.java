package com.rkassociates.UploadAppliDoc.Pages.ProfilePage.ApiCalls.ReadData;

import com.google.gson.annotations.SerializedName;

public class ReadProfileResponse {
    @SerializedName("status")
    public String status;
    @SerializedName("message")
    public String message;
    @SerializedName("data")
    public ReadProfileResult data;

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

    public ReadProfileResult getData() {
        return data;
    }

    public void setData(ReadProfileResult data) {
        this.data = data;
    }
}
