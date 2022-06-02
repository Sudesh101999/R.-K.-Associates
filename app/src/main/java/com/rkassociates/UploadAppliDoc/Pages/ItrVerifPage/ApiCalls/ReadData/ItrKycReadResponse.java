package com.rkassociates.UploadAppliDoc.Pages.ItrVerifPage.ApiCalls.ReadData;

import com.google.gson.annotations.SerializedName;

public class ItrKycReadResponse {

    @SerializedName("status")
    private String status;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private ItrKycReadResult itrKycReadResult;

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

    public ItrKycReadResult getItrKycReadResult() {
        return itrKycReadResult;
    }

    public void setItrKycReadResult(ItrKycReadResult itrKycReadResult) {
        this.itrKycReadResult = itrKycReadResult;
    }
}
