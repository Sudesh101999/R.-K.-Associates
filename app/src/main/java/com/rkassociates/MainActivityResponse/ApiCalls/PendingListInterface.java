package com.rkassociates.MainActivityResponse.ApiCalls;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface PendingListInterface {

    @FormUrlEncoded
    @POST("pending-module")
    Call<PendingListResponse> historyData(
            @Field("executive_id") String executive_id
    );

}
