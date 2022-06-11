package com.rkassociates.UploadAppliDoc.Pages.DetailItrPage.ApiCalls;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetailsItrResponse {

    @SerializedName("result")
    @Expose
    private DetailsItrResult result;
    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("msg")
    @Expose
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
