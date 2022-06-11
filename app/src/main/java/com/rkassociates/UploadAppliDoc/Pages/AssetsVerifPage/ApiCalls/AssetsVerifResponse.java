package com.rkassociates.UploadAppliDoc.Pages.AssetsVerifPage.ApiCalls;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AssetsVerifResponse {

    @SerializedName("result")
    @Expose
    private AssetsVerifResult result;
    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("msg")
    @Expose
    private String msg;

    public AssetsVerifResult getResult() {
        return result;
    }

    public void setResult(AssetsVerifResult result) {
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
