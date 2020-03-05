package com.example.hydraclient;

import com.example.hydraclient.model.hydra.token.AccessTokenResponse;
import com.example.hydraclient.model.hydra.token.GetAccessTokenByAuthorCodeBody;
import com.example.hydraclient.model.hydra.token.GetAccessTokenByRefreshTokenBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.Map;

public interface HydraPublicInterface {
    @GET("/oauth2/auth")
    Call<Object> triggerAuthorCode(@Query("response_type") String responseType, @Query("client_id") String clientId,
                                   @Query("redirect_uri") String redirectUri, @Query("scope") String scope, @Query("state") String state);

    @FormUrlEncoded
    @POST("/oauth2/token")
    Call<AccessTokenResponse> getAccessTokenByAuthorCode(@Header("Authorization") String basicAuthent, @FieldMap Map<String, String> body);

    @FormUrlEncoded
    @POST("/oauth2/token")
    Call<AccessTokenResponse> getAccessTokenByRefreshToken(@Header("Authorization") String basicAuthent, @FieldMap Map<String, String> body);
}
