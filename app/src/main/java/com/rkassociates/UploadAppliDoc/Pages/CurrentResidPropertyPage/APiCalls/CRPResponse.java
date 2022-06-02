package com.rkassociates.UploadAppliDoc.Pages.CurrentResidPropertyPage.APiCalls;

import com.google.gson.annotations.SerializedName;

public class CRPResponse {

    @SerializedName("result")
    private CRPResult result;

    @SerializedName("status")
    private int status;

    @SerializedName("msg")
    private String msg;

    public CRPResult getResult() {
        return result;
    }

    public void setResult(CRPResult result) {
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

}
