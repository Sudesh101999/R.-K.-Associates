package com.rkassociates.UploadAppliDoc.Pages.CurrentResidPropertyPage.APiCalls;

import com.google.gson.annotations.SerializedName;

public class CRPResult {
    @SerializedName("executiveId")
    private String executiveId;
    @SerializedName("applicantId")
    private String applicantId;
    @SerializedName("propertyStatus")
    private String propertyStatus;
    @SerializedName("typeOfUnit")
    private String typeOfUnit;
    @SerializedName("accessibility")
    private String accessibility;
    @SerializedName("addressConfirmed")
    private String addressConfirmed;
    @SerializedName("dimenension")
    private String dimenension;
    @SerializedName("numberOfFloors")
    private String numberOfFloors;
    @SerializedName("durationOfStay")
    private String durationOfStay;
    @SerializedName("societyNameBoard")
    private String societyNameBoard;
    @SerializedName("doorNamePlate")
    private String doorNamePlate;
    @SerializedName("utilityBills")
    private String utilityBills;
    @SerializedName("classOfLocality")
    private String classOfLocality;
    @SerializedName("interiors")
    private String interiors;
    @SerializedName("exteriors")
    private String exteriors;
    @SerializedName("remark")
    private String remark;
    @SerializedName("visitedDateTime")
    private String visitedDateTime;
    @SerializedName("residencePropertyStatus")
    private int residencePropertyStatus;

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

    public String getPropertyStatus() {
        return propertyStatus;
    }

    public void setPropertyStatus(String propertyStatus) {
        this.propertyStatus = propertyStatus;
    }

    public String getTypeOfUnit() {
        return typeOfUnit;
    }

    public void setTypeOfUnit(String typeOfUnit) {
        this.typeOfUnit = typeOfUnit;
    }

    public String getAccessibility() {
        return accessibility;
    }

    public void setAccessibility(String accessibility) {
        this.accessibility = accessibility;
    }

    public String getAddressConfirmed() {
        return addressConfirmed;
    }

    public void setAddressConfirmed(String addressConfirmed) {
        this.addressConfirmed = addressConfirmed;
    }

    public String getDimenension() {
        return dimenension;
    }

    public void setDimenension(String dimenension) {
        this.dimenension = dimenension;
    }

    public String getNumberOfFloors() {
        return numberOfFloors;
    }

    public void setNumberOfFloors(String numberOfFloors) {
        this.numberOfFloors = numberOfFloors;
    }

    public String getDurationOfStay() {
        return durationOfStay;
    }

    public void setDurationOfStay(String durationOfStay) {
        this.durationOfStay = durationOfStay;
    }

    public String getSocietyNameBoard() {
        return societyNameBoard;
    }

    public void setSocietyNameBoard(String societyNameBoard) {
        this.societyNameBoard = societyNameBoard;
    }

    public String getDoorNamePlate() {
        return doorNamePlate;
    }

    public void setDoorNamePlate(String doorNamePlate) {
        this.doorNamePlate = doorNamePlate;
    }

    public String getUtilityBills() {
        return utilityBills;
    }

    public void setUtilityBills(String utilityBills) {
        this.utilityBills = utilityBills;
    }

    public String getClassOfLocality() {
        return classOfLocality;
    }

    public void setClassOfLocality(String classOfLocality) {
        this.classOfLocality = classOfLocality;
    }

    public String getInteriors() {
        return interiors;
    }

    public void setInteriors(String interiors) {
        this.interiors = interiors;
    }

    public String getExteriors() {
        return exteriors;
    }

    public void setExteriors(String exteriors) {
        this.exteriors = exteriors;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getVisitedDateTime() {
        return visitedDateTime;
    }

    public void setVisitedDateTime(String visitedDateTime) {
        this.visitedDateTime = visitedDateTime;
    }

    public int getResidencePropertyStatus() {
        return residencePropertyStatus;
    }

    public void setResidencePropertyStatus(int residencePropertyStatus) {
        this.residencePropertyStatus = residencePropertyStatus;
    }
}
