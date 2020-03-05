package com.example.hydraclient;

import com.example.hydraclient.model.hydra.token.TokenDetail;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;

@Service
public class HydraAdminService {
    private HydraAdminInterface hydra;

    public HydraAdminService() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:4445/")
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .build();
        hydra = retrofit.create(HydraAdminInterface.class);
    }

    public TokenDetail getTokenDetail(String token) throws IOException {
        return hydra.getTokenDetail(token).execute().body();
    }
}
