package com.rkassociates.UploadAppliDoc.Pages.BusinessVerifPage.ApiCalls;

import com.rkassociates.UploadAppliDoc.Pages.BusinessVerifPage.ApiCalls.ReadData.BusinessVerifReadResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface BusinessVerifInterface {

    @FormUrlEncoded
    @POST("business-verification")
    Call<BusinessVerifResponse> businessVerifInsertData(
            @Field("executive_id") String executive_id,
            @Field("add_data_id") String add_data_id,
            @Field("name_of_business_employer") String mobile_number,
            @Field("organisation_type") String organisation_type,
            @Field("name_of_owner") String name_of_owner,
            @Field("nature_of_business") String nature_of_business,
            @Field("business_address") String business_address,
            @Field("premises_business_details") String premises_business_details,
            @Field("person_met") String person_met,
            @Field("designation") String designation,
            @Field("office_number") String office_number,
            @Field("commencement_of_business") String commencement_of_business,
            @Field("number_of_employees") String number_of_employees,
            @Field("business_reference") String business_reference,
            @Field("business_provided_docs") String business_provided_docs,
            @Field("business_acct") String business_acct,
            @Field("remark") String remark,
            @Field("date") String date
    );

    @FormUrlEncoded
    @POST("api/ItrKycVerification/business_verification_table")
    Call<BusinessVerifReadResponse> businessVerifReadData(
            @Field("executive_id") String executive_id,
            @Field("add_data_id") String add_data_id
    );
}
