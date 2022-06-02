package com.rkassociates.UploadAppliDoc.Pages.UploadImagesPage.ApiCalls;

import com.rkassociates.UploadAppliDoc.Pages.UploadImagesPage.ApiCalls.ReadData.UploadImageReadResponse;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface MultiImageInterface {

    @Multipart
    @POST("upload-picture")
    Call<ArrayList<MultiImageResponse>> uploadNewsFeedImages(
            @Part("executive_id") RequestBody executiveId,
            @Part("add_data_id") RequestBody add_data_id,
            @Part List<MultipartBody.Part> list
    );

    @FormUrlEncoded
    @POST("upload-picture")
    Call<UploadImageReadResponse> readImagesUploaded(
            @Field("add_data_id") String add_data_id,
            @Field("executive_id") String executive_id
    );

}
