package com.rkassociates.UploadAppliDoc.Pages.DetailItrPage.ApiCalls;

import com.rkassociates.UploadAppliDoc.Pages.DetailItrPage.ApiCalls.ReadData.DetailsItrReadResponse;
import com.rkassociates.UploadAppliDoc.Pages.DetailItrPage.ApiCalls.ReadYear.ReadYearResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface DetailsitrInterface {

    @FormUrlEncoded
    @POST("details-of-itr")
    Call<DetailsItrResponse> detailsItrInsertData(
            @Field("executive_id") String executive_id,
            @Field("add_data_id") String add_data_id,
            @Field("aplc_name") String aplc_name,
            @Field("assessment_year") String assessment_year,
            @Field("gti") String gti,
            @Field("deduction") String deduction,
            @Field("nti") String nti,
            @Field("tax_paid") String tax_paid,
            @Field("tds") String tds,
            @Field("refund") String refund,
            @Field("income_from_other_source") String income_from_other_source,
            @Field("it_wardasperpan") String it_wardasperpan,
            @Field("it_ward_return_filled_in") String it_ward_return_filled_in,
            @Field("return_should_filled") String return_should_filled,
            @Field("return_where_filled") String return_where_filled,
            @Field("verification") String verification,
            @Field("e_filling") String e_filling,
            @Field("date_of_filling") String date_of_filling,
            @Field("verified") String verified,
            @Field("tax_challan") String tax_challan,
            @Field("bank_name") String bank_name,
            @Field("branch_name") String branch_name,
            @Field("account_type") String account_type,
            @Field("account_number") String account_number,
            @Field("original_seen") String original_seen
    );

    @FormUrlEncoded
    @POST("api/ItrKycVerification/details_of_itr_table")
    Call<DetailsItrReadResponse> detailsItrGetData(
            @Field("add_data_id") String addDataId,
            @Field("executive_id") String executive_id
    );

    @FormUrlEncoded
    @POST("api/ItrKycVerification/itr_kyc_verification_table_year")
    Call<ReadYearResponse> readYear(
            @Field("add_data_id") String addDataId,
            @Field("executive_id") String executive_id
    );
}
