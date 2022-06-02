package com.rkassociates.UploadAppliDoc.Pages.CurrentResidPropertyPage.APiCalls.ReadData;

import com.google.gson.annotations.SerializedName;

public class CRPReadResponse {

    @SerializedName("status")
    private String status;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private CRPReadResult data;

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

    public CRPReadResult getData() {
        return data;
    }

    public void setData(CRPReadResult data) {
        this.data = data;
    }
}
