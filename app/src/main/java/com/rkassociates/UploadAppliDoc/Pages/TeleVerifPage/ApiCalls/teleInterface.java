package com.rkassociates.UploadAppliDoc.Pages.TeleVerifPage.ApiCalls;

import com.rkassociates.UploadAppliDoc.Pages.TeleVerifPage.ApiCalls.ReadData.TeleVerifResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface teleInterface {

    @FormUrlEncoded
    @POST("tele-verification-reference")
    Call<teleVerifResponse> insertTeleVerifData(
            @Field("executive_id") String executive_id,
            @Field("add_data_id") String add_data_id,
            @Field("refrence_name") String refrence_name,
            @Field("phone_number") String phone_number,
            @Field("telecalling_by") String telecalling_by,
            @Field("Date_time") String Date_time,
            @Field("conversation") String conversation,
            @Field("refrence_name1") String refrence_name1,
            @Field("phone_number1") String phone_number1,
            @Field("telecalling_by1") String telecalling_by1,
            @Field("conversation1") String conversation1
    );

    @FormUrlEncoded
    @POST("api/ItrKycVerification/tele_verification_reference_table")
    Call<TeleVerifResponse> readTeleVerifData(
            @Field("add_data_id") String add_data_id,
            @Field("executive_id") String executive_id
    );

}
