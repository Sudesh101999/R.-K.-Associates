package com.rkassociates.UploadAppliDoc.Pages.TeleVerifPage.ApiCalls;

import com.google.gson.annotations.SerializedName;

public class teleVerifResponse {
    @SerializedName("result")
    private int result;
    @SerializedName("status")
    private int status;
    @SerializedName("msg")
    private String msg;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
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
