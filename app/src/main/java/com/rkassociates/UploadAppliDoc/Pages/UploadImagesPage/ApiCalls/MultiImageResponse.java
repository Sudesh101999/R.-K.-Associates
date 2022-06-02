package com.rkassociates.UploadAppliDoc.Pages.UploadImagesPage.ApiCalls;

import com.google.gson.annotations.SerializedName;

public class MultiImageResponse {


    @SerializedName("result")
    private MultiImageResult result;

    @SerializedName("status")
    private int status;

    @SerializedName("msg")
    private String msg;

    public MultiImageResult getResult() {
        return result;
    }

    public void setResult(MultiImageResult result) {
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
