package com.rkassociates.DocPicker.ApiCalling;

import java.util.ArrayList;
import java.util.List;

public class bankBranchSpinner {
    public List<String> banks = new ArrayList<String>();
    public List<String> branches = new ArrayList<String>();
    public boolean status;
    public String message;

    public List<String> getBanks() {
        return banks;
    }

    public void setBanks(List<String> banks) {
        this.banks = banks;
    }

    public List<String> getBranches() {
        return branches;
    }

    public void setBranches(List<String> branches) {
        this.branches = branches;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
