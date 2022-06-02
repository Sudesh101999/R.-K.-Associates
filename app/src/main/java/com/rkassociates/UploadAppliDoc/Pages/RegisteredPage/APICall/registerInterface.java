package com.rkassociates.UploadAppliDoc.Pages.RegisteredPage.APICall;

import com.rkassociates.UploadAppliDoc.Pages.RegisteredPage.APICall.CoAplcResult.coAplcResponse;
import com.rkassociates.UploadAppliDoc.Pages.RegisteredPage.APICall.ReadData.RegisteredResponseRead;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface registerInterface {

    @FormUrlEncoded
    @POST("applicant")
    Call<registeredResponse> RegisteredApplicantCall(
            @Field("add_data_id") String  add_data_id,
            @Field("executive_id") String  executive_id,
            @Field("applicant_name") String  applicant_name,
            @Field("residence_verification") String residence_verification,
            @Field("business_verification") String business_verification,
            @Field("residence") String residence,
            @Field("work") String  work,
            @Field("ITR_verification") String ITR_verification,
            @Field("TDS_certificate_verification") String  TDS_certificate_verification,
            @Field("bank_statement_verification") String bank_statement_verification,
            @Field("change_property_verification") String  change_property_verification,
            @Field("pan_number") String pan_number,
            @Field("aadhaar_number") String  aadhaar_number,
            @Field("electricity_bill") String electricity_bill,
            @Field("driving_license") String  driving_license
    );
    @FormUrlEncoded
    @POST("co-applicant")
    Call<coAplcResponse> RegisteredCoApplicantCall(
            @Field("add_data_id") String  add_data_id,
            @Field("executive_id") String  executive_id,
            @Field("co_applicant_name") String  applicant_name,
            @Field("residence_verification") String residence_verification,
            @Field("business_verification") String business_verification,
            @Field("residence") String residence,
            @Field("work") String  work,
            @Field("ITR_verification") String ITR_verification,
            @Field("TDS_certificate_verification") String  TDS_certificate_verification,
            @Field("bank_statement_verification") String bank_statement_verification,
            @Field("change_property_verification") String  change_property_verification,
            @Field("pan_number") String pan_number,
            @Field("aadhaar_number") String  aadhaar_number,
            @Field("electricity_bill") String electricity_bill,
            @Field("driving_license") String  driving_license
    );

    @FormUrlEncoded
    @POST("api/ItrKycVerification/get_user_reg_module")
    Call<RegisteredResponseRead> RegisteredApplicantCall(
            @Field("add_data_id") String  add_data_id,
            @Field("executive_id") String  executive_id
    );
}
