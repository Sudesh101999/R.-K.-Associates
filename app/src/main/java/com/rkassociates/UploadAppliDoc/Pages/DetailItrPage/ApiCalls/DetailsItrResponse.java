package com.rkassociates.UploadAppliDoc.Pages.DetailItrPage.ApiCalls;

import com.google.gson.annotations.SerializedName;

public class DetailsItrResponse {

    @SerializedName("result")
    private DetailsItrResult result;

    @SerializedName("status")
    private int status;

    @SerializedName("msg")
    private String msg;

    public DetailsItrResult getResult() {
        return result;
    }

    public void setResult(DetailsItrResult result) {
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
