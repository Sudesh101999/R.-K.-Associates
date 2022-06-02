package com.rkassociates.MainActivityResponse;

public class MainActivityModel {
    String pending,add_data_id,name;

    public MainActivityModel(String pending, String add_data_id, String name) {
        this.pending = pending;
        this.add_data_id = add_data_id;
        this.name = name;
    }

    public String getPending() {
        return pending;
    }

    public void setPending(String pending) {
        this.pending = pending;
    }

    public String getAdd_data_id() {
        return add_data_id;
    }

    public void setAdd_data_id(String add_data_id) {
        this.add_data_id = add_data_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
