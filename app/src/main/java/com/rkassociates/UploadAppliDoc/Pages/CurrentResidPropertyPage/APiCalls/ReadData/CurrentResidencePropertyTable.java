package com.rkassociates.UploadAppliDoc.Pages.CurrentResidPropertyPage.APiCalls.ReadData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CurrentResidencePropertyTable {
    @SerializedName("residence_property_id")
    @Expose
    private String residencePropertyId;
    @SerializedName("executive_id")
    @Expose
    private String executiveId;
    @SerializedName("add_data_id")
    @Expose
    private String addDataId;
    @SerializedName("property_status")
    @Expose
    private String propertyStatus;
    @SerializedName("type_of_unit")
    @Expose
    private String typeOfUnit;
    @SerializedName("accessibility")
    @Expose
    private String accessibility;
    @SerializedName("address_confirmed")
    @Expose
    private String addressConfirmed;
    @SerializedName("dimenension")
    @Expose
    private String dimenension;
    @SerializedName("number_of_floors")
    @Expose
    private String numberOfFloors;
    @SerializedName("door_name_plate")
    @Expose
    private String doorNamePlate;
    @SerializedName("duration_of_stay")
    @Expose
    private String durationOfStay;
    @SerializedName("society_name_board")
    @Expose
    private String societyNameBoard;
    @SerializedName("utility_bills")
    @Expose
    private String utilityBills;
    @SerializedName("class_of_locality")
    @Expose
    private String classOfLocality;
    @SerializedName("interiors")
    @Expose
    private String interiors;
    @SerializedName("exteriors")
    @Expose
    private String exteriors;
    @SerializedName("remark")
    @Expose
    private String remark;
    @SerializedName("buying_property")
    @Expose
    private String buyingProperty;
    @SerializedName("visited_date_time")
    @Expose
    private String visitedDateTime;
    @SerializedName("residence_property_status")
    @Expose
    private String residencePropertyStatus;
    @SerializedName("created_date")
    @Expose
    private String createdDate;
    @SerializedName("applicant_name")
    @Expose
    private String applicantName;

    public String getResidencePropertyId() {
        return residencePropertyId;
    }

    public void setResidencePropertyId(String residencePropertyId) {
        this.residencePropertyId = residencePropertyId;
    }

    public String getExecutiveId() {
        return executiveId;
    }

    public void setExecutiveId(String executiveId) {
        this.executiveId = executiveId;
    }

    public String getAddDataId() {
        return addDataId;
    }

    public void setAddDataId(String addDataId) {
        this.addDataId = addDataId;
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

    public String getDoorNamePlate() {
        return doorNamePlate;
    }

    public void setDoorNamePlate(String doorNamePlate) {
        this.doorNamePlate = doorNamePlate;
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

    public String getBuyingProperty() {
        return buyingProperty;
    }

    public void setBuyingProperty(String buyingProperty) {
        this.buyingProperty = buyingProperty;
    }

    public String getVisitedDateTime() {
        return visitedDateTime;
    }

    public void setVisitedDateTime(String visitedDateTime) {
        this.visitedDateTime = visitedDateTime;
    }

    public String getResidencePropertyStatus() {
        return residencePropertyStatus;
    }

    public void setResidencePropertyStatus(String residencePropertyStatus) {
        this.residencePropertyStatus = residencePropertyStatus;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }
}