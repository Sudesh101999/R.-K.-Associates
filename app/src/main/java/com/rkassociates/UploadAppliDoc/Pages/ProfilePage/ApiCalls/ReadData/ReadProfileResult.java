package com.rkassociates.UploadAppliDoc.Pages.ProfilePage.ApiCalls.ReadData;

import com.google.gson.annotations.SerializedName;

public class ReadProfileResult {
    @SerializedName("profile_remark_table")
    private ProfileRemarkTable profileRemarkTable;

    public ProfileRemarkTable getProfileRemarkTable() {
        return profileRemarkTable;
    }

    public void setProfileRemarkTable(ProfileRemarkTable profileRemarkTable) {
        this.profileRemarkTable = profileRemarkTable;
    }
}
