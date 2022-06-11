package com.rkassociates.HistoryList.ApiCalls;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HistoryListResult {
    @SerializedName("add_data_id")
    @Expose
    private String addDataId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("bank_name")
    @Expose
    private String bankName;
    @SerializedName("branch_name")
    @Expose
    private String branchName;

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

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }
}
