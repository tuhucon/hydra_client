package com.example.hydraclient.model.hydra.token;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class GetAccessTokenByAuthorCodeBody {
    @JsonProperty("grant_type")
    private final String grantType = "authorization_code";

    private String code;

    @JsonProperty("client_id")
    private String clientId;

    @JsonProperty("redirect_uri")
    private String redirectUri;

    public Map<String, String> toMap() {
        Map<String, String> result = new HashMap<>();
        result.put("grant_type", grantType);
        result.put("client_id", clientId);
        result.put("code", code);
        result.put("redirect_uri", redirectUri);
        return result;
    }
}
