package com.rkassociates.UploadAppliDoc.Pages.RegisteredPage.APICall.ReadData;

import com.google.gson.annotations.SerializedName;

public class RegisteredResponseRead {

    @SerializedName("data")
    public registeredReadData result;

    @SerializedName("status")
    public String status;

    @SerializedName("message")
    public String message;

    public registeredReadData getResult() {
        return result;
    }

    public void setResult(registeredReadData result) {
        this.result = result;
    }

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
}
