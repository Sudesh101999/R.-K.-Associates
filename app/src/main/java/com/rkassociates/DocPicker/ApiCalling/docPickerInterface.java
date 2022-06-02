package com.rkassociates.DocPicker.ApiCalling;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface docPickerInterface {
    @GET("api_bank_list")
    Call<bankBranchSpinner>  spinnerCallApi();


    @FormUrlEncoded
    @POST("add-data")
    Call<addDataResponse> insertAddData(
            @Field("executive_id") String employee_name,
            @Field("bank_name") String bank_name,
            @Field("branch_name") String branch_name,
            @Field("reference_name") String reference_name,
            @Field("applicant_name") String applicant_name,
            @Field("co_applicant_name") String co_applicant_name,
            @Field("product_type") String product_type,
            @Field("loan_amount") String loan_amount,
            @Field("submit_date") String submit_date
    );
}
