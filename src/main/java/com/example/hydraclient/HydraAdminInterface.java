package com.example.hydraclient;

import com.example.hydraclient.model.hydra.token.TokenDetail;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface HydraAdminInterface {
    @FormUrlEncoded
    @POST("/oauth2/introspect")
    Call<TokenDetail> getTokenDetail(@Field("token") String token);
}
