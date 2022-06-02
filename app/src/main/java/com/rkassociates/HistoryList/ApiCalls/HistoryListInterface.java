package com.rkassociates.HistoryList.ApiCalls;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface HistoryListInterface {
    @FormUrlEncoded
    @POST("completed-module")
    Call<HistoryListResponse> getHistoryResponse(
            @Field("executive_id") String executive_id
    );

}
