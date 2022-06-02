package com.rkassociates.UploadAppliDoc.Pages.CurrentResidenceVerifPage.ApiCalls;

import com.google.gson.annotations.SerializedName;
import com.rkassociates.UploadAppliDoc.Pages.CurrentResidenceVerifPage.ApiCalls.ReadData.CurrentResidenceVerificationTable;

public class CurrentResidVerifResponse {

    @SerializedName("result")
    private CurrentResidVerifResult result;

    @SerializedName("status")
    private int status;

    @SerializedName("msg")
    private String msg;

    @SerializedName("current_residence_verification_table")
    private CurrentResidenceVerificationTable currentResidenceVerificationTable;

    public CurrentResidVerifResult getResult() {
        return result;
    }

    public void setResult(CurrentResidVerifResult result) {
        this.result = result;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public CurrentResidenceVerificationTable getCurrentResidenceVerificationTable() {
        return currentResidenceVerificationTable;
    }

    public void setCurrentResidenceVerificationTable(CurrentResidenceVerificationTable currentResidenceVerificationTable) {
        this.currentResidenceVerificationTable = currentResidenceVerificationTable;
    }
}
