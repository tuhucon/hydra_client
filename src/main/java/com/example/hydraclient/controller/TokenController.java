package com.example.hydraclient.controller;

import com.example.hydraclient.HydraAdminService;
import com.example.hydraclient.HydraPublicService;
import com.example.hydraclient.model.hydra.token.AccessTokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class TokenController {
    private final HydraPublicService hydraPublicService;
    private final HydraAdminService hydraAdminService;

    private String clientId = "tuhucon";
    private String clientSecret = "tuhucon";

    @GetMapping("/start")
    public void start(HttpServletResponse response) {
        response.setHeader("Location", "http://127.0.0.1:4444/oauth2/auth?response_type=code&" +
                "client_id=tuhucon&redirect_uri=http%3A%2F%2F127.0.0.1%3A3333%2Fcallback&scope=%2A&state=" +
                String.valueOf(System.currentTimeMillis()));
        response.setStatus(302);
    }

    @GetMapping("/callback")
    public Map<String, Object> callback(@RequestParam String code) throws IOException {
        Map<String, Object> result = new HashMap<>();

        //call to hydra to get access token via authorization code
        AccessTokenResponse firstToken = hydraPublicService.getAccessTokenByAuthorCode(getBasicAuthent(), code, clientId, "http://127.0.0.1:3333/callback");
        result.put("result", firstToken);

        //intropective token
        result.put("access token detail", hydraAdminService.getTokenDetail(firstToken.getAccessToken()));
        result.put("refresh token detail", hydraAdminService.getTokenDetail(firstToken.getRefreshToken()));

        //call to refresh token
        AccessTokenResponse secondToken = hydraPublicService.getAccessTokenByRefreshToken(getBasicAuthent(), firstToken.getRefreshToken(), clientId);
        result.put("new result", secondToken);
        //intropective token
        result.put("new access token detail", hydraAdminService.getTokenDetail(secondToken.getAccessToken()));
        result.put("new refresh token detail", hydraAdminService.getTokenDetail(secondToken.getRefreshToken()));

        return result;
    }

    private String getBasicAuthent() {
        // concatenate username and password with colon for authentication
        String credentials = clientId + ":" + clientSecret;

        // create Base64 encodet string
        return "Basic " + Base64Utils.encodeToString(credentials.getBytes());
    }


}
