package com.rkassociates.DocPicker.ApiCalling;

import com.google.gson.annotations.SerializedName;

public class addDataResponse {

    @SerializedName("result")
    public Result result;

    @SerializedName("status")
    public int status;

    @SerializedName("msg")
    public String msg;

    @SerializedName("add_data_id")
    public int add_data_id;

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

    public int getAdd_data_id() {
        return add_data_id;
    }

    public void setAdd_data_id(int add_data_id) {
        this.add_data_id = add_data_id;
    }
}
