package com.rkassociates.UploadAppliDoc.Pages.DetailItrPage.ApiCalls.ReadData;

import com.google.gson.annotations.SerializedName;

public class DetailsItrReadResponse {
    @SerializedName("status")
    private String status;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private DetailsItrReadResult result;

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

    public DetailsItrReadResult getResult() {
        return result;
    }

    public void setResult(DetailsItrReadResult result) {
        this.result = result;
    }
}
