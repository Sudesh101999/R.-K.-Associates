package com.rkassociates.UploadAppliDoc.Pages.BusinessVerifPage.ApiCalls.ReadData;

import com.google.gson.annotations.SerializedName;

public class BusinessVerifReadResponse {

    @SerializedName("status")
    private String status;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private BusinessVerifReadResult data;

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

    public BusinessVerifReadResult getResult() {
        return data;
    }

    public void setResult(BusinessVerifReadResult data) {
        this.data = data;
    }
}
