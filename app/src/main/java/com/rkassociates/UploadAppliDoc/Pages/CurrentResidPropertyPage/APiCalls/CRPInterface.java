package com.rkassociates.UploadAppliDoc.Pages.CurrentResidPropertyPage.APiCalls;

import com.rkassociates.UploadAppliDoc.Pages.CurrentResidPropertyPage.APiCalls.ReadData.CRPReadResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface CRPInterface {

    @FormUrlEncoded
    @POST("current-residence-property")
    Call<CRPResponse> CRPInsertData(
            @Field("executive_id") String executive_id,
            @Field("add_data_id") String add_data_id,
            @Field("property_status") String property_status,
            @Field("type_of_unit") String type_of_unit,
            @Field("accessibility") String accessibility,
            @Field("address_confirmed") String address_confirmed,
            @Field("dimenension") String dimenension,
            @Field("number_of_floors") String number_of_floors,
            @Field("duration_of_stay") String duration_of_stay,
            @Field("society_name_board") String society_name_board,
            @Field("door_name_plate") String door_name_plate,
            @Field("utility_bills") String utility_bills,
            @Field("class_of_locality") String class_of_locality,
            @Field("interiors") String interiors,
            @Field("exteriors") String exteriors,
            @Field("remark") String remark,
            @Field("visited_date_time") String visited_date_time
    );

    @FormUrlEncoded
    @POST("api/ItrKycVerification/current_residence_property_table")
    Call<CRPReadResponse> CRPReadData(
            @Field("add_data_id") String add_data_id,
            @Field("executive_id") String executive_id
    );

}
