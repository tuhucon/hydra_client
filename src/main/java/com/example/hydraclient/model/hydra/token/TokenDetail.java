package com.example.hydraclient.model.hydra.token;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TokenDetail {
    @JsonProperty("client_id")
    private String clientId;

    @JsonProperty("active")
    private Boolean active;

    @JsonProperty("scope")
    private String scope;

    @JsonProperty("sub")
    private String userId;

    @JsonProperty("exp")
    private String expiredAt;

    @JsonProperty("token_type")
    private String tokenType;
}
