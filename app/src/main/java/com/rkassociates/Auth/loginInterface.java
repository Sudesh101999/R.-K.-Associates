package com.rkassociates.Auth;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface loginInterface {
    @FormUrlEncoded
    @POST("php_api_rk.php?apicall=login")
    Call<loginResponseModel> loginUser(
            @Field("email") String  User_id,
            @Field("password") String image
    );
    @FormUrlEncoded
    @POST("app-login")
    Call<loginResponseModel> newLogin(
            @Field("email") String  User_id,
            @Field("password") String image
    );

}
