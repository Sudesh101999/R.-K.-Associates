package com.rkassociates.UploadAppliDoc.Pages.ProfilePage.ApiCalls;

import com.google.gson.annotations.SerializedName;

public class ProfileResponse {

    @SerializedName("result")
    private ProfileResult result;

    @SerializedName("status")
    private int status;

    @SerializedName("msg")
    private String msg;

    public ProfileResult getResult() {
        return result;
    }

    public void setResult(ProfileResult result) {
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
