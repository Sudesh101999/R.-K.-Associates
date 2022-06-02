package com.rkassociates.UploadAppliDoc.Pages.ProfilePage.ApiCalls;

import com.rkassociates.UploadAppliDoc.Pages.ProfilePage.ApiCalls.ReadData.ReadProfileResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ProfileInterface {

    @FormUrlEncoded
    @POST("profile-remark")
    Call<ProfileResponse> profileDataInsert(
            @Field("executive_id") String  executive_id,
            @Field("add_data_id") String add_data_id,
            @Field("aplc_name") String aplcNameStr,
            @Field("residence") String  residence,
            @Field("duration_of_stay") String  duration_of_stay,
            @Field("qulification") String  qulification,
            @Field("business_services") String  business_services,
            @Field("employee_name") String  employee_name,
            @Field("designation") String  designation,
            @Field("commencement_of_business") String  commencement_of_business,
            @Field("profile_remark_status") String  profile_remark_status,
            @Field("submission_date") String  submission_date

    );

    @FormUrlEncoded
    @POST("api/ItrKycVerification/profile_remark_table")
    Call<ReadProfileResponse> readProfileData(
            @Field("add_data_id") String  add_data_id,
            @Field("executive_id") String  executive_id
    );

}
