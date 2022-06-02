package com.rkassociates.UploadAppliDoc.Pages.RegisteredPage.APICall;

import com.google.gson.annotations.SerializedName;

public class registeredResponse {
    @SerializedName("result")
    public Result result;

    @SerializedName("status")
    public int status;

    @SerializedName("msg")
    public String msg;

    @SerializedName("applicant_id")
    public String applicant_id;


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

    public String getApplicant_id() {
        return applicant_id;
    }

    public void setApplicant_id(String applicant_id) {
        this.applicant_id = applicant_id;
    }
}
