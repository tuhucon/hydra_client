package com.example.hydraclient;

import com.example.hydraclient.model.hydra.token.AccessTokenResponse;
import com.example.hydraclient.model.hydra.token.GetAccessTokenByAuthorCodeBody;
import com.example.hydraclient.model.hydra.token.GetAccessTokenByRefreshTokenBody;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;

@Service
public class HydraPublicService {
    private HydraPublicInterface hydra;

    public HydraPublicService() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:4444/")
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .build();
        hydra = retrofit.create(HydraPublicInterface.class);
    }

    public AccessTokenResponse getAccessTokenByAuthorCode(String basicAuthent, String code, String clientId, String redirectUri) throws IOException {
        GetAccessTokenByAuthorCodeBody body = new GetAccessTokenByAuthorCodeBody();
        body.setClientId(clientId);
        body.setCode(code);
        body.setRedirectUri(redirectUri);
        return hydra.getAccessTokenByAuthorCode(basicAuthent, body.toMap()).execute().body();
    }

    public AccessTokenResponse getAccessTokenByRefreshToken(String basicAuthent, String refreshToken, String clientId) throws IOException {
        GetAccessTokenByRefreshTokenBody body = new GetAccessTokenByRefreshTokenBody();
        body.setClientId(clientId);
        body.setRefreshToken(refreshToken);
        return hydra.getAccessTokenByRefreshToken(basicAuthent, body.toMap()).execute().body();
    }
}
