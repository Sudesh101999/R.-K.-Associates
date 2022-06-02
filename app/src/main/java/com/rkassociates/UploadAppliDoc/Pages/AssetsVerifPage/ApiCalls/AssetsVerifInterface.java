package com.rkassociates.UploadAppliDoc.Pages.AssetsVerifPage.ApiCalls;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface AssetsVerifInterface {

    @FormUrlEncoded
    @POST("asset-verification")
    Call<AssetsVerifResponse> insertAssetsVerifWithHome(
            @Field("executive_id") String  executive_id,
            @Field("add_data_id") String add_data_id,
            @Field("aplc_name") String aplcNameStr,
            @Field("person_met") String  person_met,
            @Field("phone_number") String  phone_number,
            @Field("doc_of_ownership") String  doc_of_ownership,
            @Field("margin_money_payment") String  margin_money_payment,
            @Field("seller_acc_verified") String  seller_acc_verified,
            @Field("bank_charge") String  bank_charge,
            @Field("NOC_issue") String  NOC_issue,
            @Field("agreement_value") String  agreement_value,
            @Field("stamp_duty") String  stamp_duty,
            @Field("registration") String  registration,
            @Field("index") String  index,
            @Field("pancard_number") String  pancard_number,

            @Field("product_type") String  product_type,

            @Field("property_status") String  property_status,
            @Field("type_of_unit") String  type_of_unit,
            @Field("assessbility") String  assessbility,
            @Field("confirm_address") String  confirm_address,
            @Field("sq_fit") String  sq_fit,
            @Field("flat_type") String  flat_type,
            @Field("floor_no") String  floor_no,
            @Field("duration_of_stay") String  duration_of_stay,
            @Field("socity_name_board") String  socity_name_board,
            @Field("door_name_plat") String  door_name_plat,
            @Field("utility_bill") String  utility_bill,
            @Field("locality") String  locality,
            @Field("interiors") String  interiors,
            @Field("exteriors") String  exteriors,
            @Field("remark") String  remark,

            @Field("address_visited") String  address_visited,
            @Field("car_person_met") String  car_person_met,

            @Field("mortgage_type") String  mortgage_type,
            @Field("area") String  area,
            @Field("ownership_land") String  ownership_land,

            @Field("type_of_machinery") String  type_of_machinery,
            @Field("raw_material") String  raw_material,
            @Field("finish_wood") String  finish_wood,

            @Field("v_date") String  v_date,
            @Field("v_time") String  v_time
    );

    @FormUrlEncoded
    @POST("asset-verification")
    Call<AssetsVerifResponse> insertAssetsVerif(
            @Field("executive_id") String  executive_id,
            @Field("add_data_id") String add_data_id,
            @Field("aplcNameStr") String aplcNameStr,
            @Field("phone_number") String  phone_number,
            @Field("doc_of_ownership") String  doc_of_ownership,
            @Field("margin_money_payment") String  margin_money_payment,
            @Field("seller_acc_verified") String  seller_acc_verified,
            @Field("bank_charge") String  bank_charge,
            @Field("NOC_issue") String  NOC_issue,
            @Field("agreement_value") String  agreement_value,
            @Field("stamp_duty") String  stamp_duty,
            @Field("registration") String  registration,
            @Field("index") String  index,
            @Field("pancard_number") String  pancard_number,
            @Field("product_type") String  product_type,
            @Field("address_visited") String  address_visited,
            @Field("person_met") String  person_met,
            @Field("mortgage_type") String  mortgage_type,
            @Field("area") String  area,
            @Field("ownership_land") String  ownership_land,
            @Field("type_of_machinery") String  type_of_machinery,
            @Field("raw_material") String  raw_material,
            @Field("finish_wood") String  finish_wood,
            @Field("v_date") String  v_date,
            @Field("v_time") String  v_time
    );
}
