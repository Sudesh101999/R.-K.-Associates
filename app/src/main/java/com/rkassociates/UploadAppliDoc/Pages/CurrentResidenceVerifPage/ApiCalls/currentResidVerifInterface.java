package com.rkassociates.UploadAppliDoc.Pages.CurrentResidenceVerifPage.ApiCalls;

import com.rkassociates.UploadAppliDoc.Pages.CurrentResidenceVerifPage.ApiCalls.ReadData.CurrentResidVerifReadResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface currentResidVerifInterface {
    @FormUrlEncoded
    @POST("current-residence-verification")
    Call<CurrentResidVerifResponse> currentResidVefiInsert(
            @Field("executive_id") String  executive_id,
            @Field("add_data_id") String add_data_id,
            @Field("aplc_name") String aplcName,
            @Field("mobile_number") String  mobile_number,
            @Field("email") String  email,
            @Field("DOB") String  DOB,
            @Field("gender") String  gender,
            @Field("qualification") String  qualification,
            @Field("marital_status") String  marital_status,
            @Field("adharcard_number") String  adharcard_number,
            @Field("utility_bill") String  utility_bill,
            @Field("application_relation") String  application_relation,
            @Field("family_mambers") String  family_mambers,
            @Field("earning_members") String  earning_members,
            @Field("children") String  children,
            @Field("current_address") String  current_address
    );


    @FormUrlEncoded
    @POST("api/ItrKycVerification/current_residence_verification_table")
    Call<CurrentResidVerifReadResponse> getCurrentResidVefi(
            @Field("add_data_id") String  add_data_id,
            @Field("executive_id") String  executive_id
    );
}
