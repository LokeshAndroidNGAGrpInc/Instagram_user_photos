package com.dev.pyar;

import com.dev.pyar.model.PhotoModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by user2 on 8/1/2017.
 */

public interface ServiceOperations {
    @FormUrlEncoded
    @POST("oauth/access_token")
    Call<Example> getAccessToken(@Field("client_id") String clientid,
                                 @Field("client_secret") String clientSecret,
                                 @Field("grant_type") String grantType,
                                 @Field("redirect_uri") String redirectUri,
                                 @Field("code") String code);

    @GET("v1/users/self/media/recent")
    Call<PhotoModel> getUserPhotos(@Query("client_id") String clientId,@Query("access_token") String accessToken);
}
