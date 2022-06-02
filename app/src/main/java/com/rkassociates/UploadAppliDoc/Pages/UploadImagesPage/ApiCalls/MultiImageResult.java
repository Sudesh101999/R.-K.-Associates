package com.rkassociates.UploadAppliDoc.Pages.UploadImagesPage.ApiCalls;

import com.google.gson.annotations.SerializedName;

public class MultiImageResult {

    @SerializedName("executiveId")
    private String executiveId;
    @SerializedName("applicantId")
    private String applicantId;
    @SerializedName("picture")
    private String picture;
    @SerializedName("uploadImageStatus")
    private int uploadImageStatus;

    public String getExecutiveId() {
        return executiveId;
    }

    public void setExecutiveId(String executiveId) {
        this.executiveId = executiveId;
    }

    public String getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(String applicantId) {
        this.applicantId = applicantId;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getUploadImageStatus() {
        return uploadImageStatus;
    }

    public void setUploadImageStatus(int uploadImageStatus) {
        this.uploadImageStatus = uploadImageStatus;
    }
}
