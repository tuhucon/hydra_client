package com.example.hydraclient.model.hydra.token;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class GetAccessTokenByRefreshTokenBody {
    @JsonProperty("grant_type")
    private final String grantType = "refresh_token";

    @JsonProperty("refresh_token")
    private String refreshToken;

    @JsonProperty("client_id")
    private String clientId;

    public Map<String, String> toMap() {
        Map<String, String> result = new HashMap<>();
        result.put("grant_type", grantType);
        result.put("refresh_token", refreshToken);
        result.put("client_id", clientId);
        return result;
    }
}
