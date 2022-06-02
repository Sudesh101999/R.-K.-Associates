package com.rkassociates.UploadAppliDoc.Pages.ItrVerifPage.ApiCalls;

import com.rkassociates.UploadAppliDoc.Pages.ItrVerifPage.ApiCalls.ReadData.ItrKycReadResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface itrKycVerifInterface {

    @FormUrlEncoded
    @POST("itr-kyc-verification")
    Call<itrResponse> itrKycVefiInsert(
            @Field("executive_id") String  executive_id,
            @Field("add_data_id") String add_data_id,
            @Field("aplc_name") String aplc_name,
            @Field("year1") String  year1,
            @Field("year2") String  year2,
            @Field("year3") String  year3,
            @Field("year_remark") String  year_remark,
            @Field("pancard_number") String  pancard_number,
            @Field("pancard_remark") String  pancard_remark,
            @Field("aadharcard_number") String  aadharcard_number,
            @Field("aadharcard_remark") String  aadharcard_remark,
            @Field("electricity_bill") String  electricity_bill,
            @Field("electricity_remark") String  electricity_remark,
            @Field("bank_name") String  bank_name,
            @Field("bank_remark") String  bank_remark,
            @Field("final_remark") String  final_remark,
            @Field("co_applicant_id") String  co_applicant_id,
            @Field("co_applicant_year1") String  co_applicant_year1,
            @Field("co_applicant_year2") String  co_applicant_year2,
            @Field("co_applicant_year3") String  co_applicant_year3,
            @Field("co_applicant_year_remark") String  co_applicant_year_remark,
            @Field("co_applicant_pancard_number") String  co_applicant_pancard_number,
            @Field("co_applicant_pancard_remark") String  co_applicant_pancard_remark,
            @Field("co_applicant_aadharcard_number") String  co_applicant_aadharcard_number,
            @Field("co_applicant_aadharcard_remark") String  co_applicant_aadharcard_remark,
            @Field("co_applicant_electricity_bill") String  co_applicant_electricity_bill,
            @Field("co_applicant_electricity_remark") String  co_applicant_electricity_remark,
            @Field("co_applicant_bank_name") String  co_applicant_bank_name,
            @Field("co_applicant_bank_remark") String  co_applicant_bank_remark,
            @Field("co_applicant_final_remark") String  co_applicant_final_remark
    );

    @FormUrlEncoded
    @POST("api/ItrKycVerification/itr_kyc_verification_table")
    Call<ItrKycReadResponse> itrKycVefiRead(
            @Field("add_data_id") String  add_data_id,
            @Field("executive_id") String  executive_id
    );
}
