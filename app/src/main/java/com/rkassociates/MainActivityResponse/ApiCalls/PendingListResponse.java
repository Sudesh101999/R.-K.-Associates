package com.rkassociates.MainActivityResponse.ApiCalls;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class PendingListResponse {

    @SerializedName("status")
    public String status;
    @SerializedName("message")
    public String message;
    @SerializedName("data")
    public List<PendingListResult> data = new ArrayList<PendingListResult>();

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<PendingListResult> getData() {
        return data;
    }

    public void setData(List<PendingListResult> data) {
        this.data = data;
    }
}
