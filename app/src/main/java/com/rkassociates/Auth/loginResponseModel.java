package com.rkassociates.Auth;

import com.google.gson.annotations.SerializedName;

public class loginResponseModel {

    @SerializedName("result")
    public Result ResultObject;

    @SerializedName("status")
    public int status;

    @SerializedName("msg")
    public String msg;


    // Getter Methods

    public Result getResult() {
        return ResultObject;
    }

    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    // Setter Methods

    public void setResult(Result resultObject) {
        this.ResultObject = resultObject;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}