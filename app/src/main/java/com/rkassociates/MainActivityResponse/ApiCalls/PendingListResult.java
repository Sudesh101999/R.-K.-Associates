package com.rkassociates.MainActivityResponse.ApiCalls;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class PendingListResult {
    @SerializedName("pending")
    private List<String> pending = new ArrayList<String>();
    @SerializedName("add_data_id")
    private String addDataId;
    @SerializedName("name")
    private String name;

    public List<String> getPending() {
        return pending;
    }

    public void setPending(List<String> pending) {
        this.pending = pending;
    }

    public String getAddDataId() {
        return addDataId;
    }

    public void setAddDataId(String addDataId) {
        this.addDataId = addDataId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
