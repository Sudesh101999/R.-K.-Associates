package com.rkassociates.UploadAppliDoc.Pages.BusinessVerifPage.ApiCalls;

import com.google.gson.annotations.SerializedName;

public class BusinessVerifResponse {

    @SerializedName("result")
    private BusinessVerifResult result;

    @SerializedName("status")
    private int status;

    @SerializedName("msg")
    private String msg;


    public BusinessVerifResult getResult() {
        return result;
    }

    public void setResult(BusinessVerifResult result) {
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
