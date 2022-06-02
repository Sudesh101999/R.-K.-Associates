package com.rkassociates.UploadAppliDoc.Pages.RegisteredPage.APICall.CoAplcResult;

import com.google.gson.annotations.SerializedName;

public class coAplcResponse {
    @SerializedName("result")
    public Result result;

    @SerializedName("status")
    public int status;

    @SerializedName("msg")
    public String msg;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
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
