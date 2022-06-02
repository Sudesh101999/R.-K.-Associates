package com.rkassociates.UploadAppliDoc.Pages.UploadImagesPage.ApiCalls.ReadData;

import com.google.gson.annotations.SerializedName;

public class UploadImageTable {
    @SerializedName("upload_image_id")
    private String uploadImageId;
    @SerializedName("executive_id")
    private String executiveId;
    @SerializedName("applicant_id")
    private String applicantId;
    @SerializedName("picture")
    private String picture;
    @SerializedName("upload_image_status")
    private String uploadImageStatus;
    @SerializedName("created_date")
    private String createdDate;

    public String getUploadImageId() {
        return uploadImageId;
    }

    public void setUploadImageId(String uploadImageId) {
        this.uploadImageId = uploadImageId;
    }

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

    public String getUploadImageStatus() {
        return uploadImageStatus;
    }

    public void setUploadImageStatus(String uploadImageStatus) {
        this.uploadImageStatus = uploadImageStatus;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
}
