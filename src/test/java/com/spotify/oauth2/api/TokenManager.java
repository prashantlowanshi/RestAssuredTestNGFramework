package com.spotify.oauth2.api;

import com.spotify.oauth2.utils.ConfigLoader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
// import org.joda.time.Instant;
import java.io.ObjectInputFilter;
import java.time.Instant;
import java.util.HashMap;

import static com.spotify.oauth2.api.SpecBuilder.getResponseSpec;
import static io.restassured.RestAssured.given;

public class TokenManager {
    private static String access_token;
    private static Instant expiry_time;

    public synchronized static String getToken() {
        try {
            if (access_token == null || Instant.now().isAfter(expiry_time)) {
                System.out.println("Renewing token.....");
                Response response = renewToken();
                System.out.println("Renewing token.....");
                access_token = response.path("access_token");
                System.out.println("Access token is " + access_token);
                int expiryDurationInSeconds = response.path("expires_in");
                System.out.println("expiryDurationInSeconds is " + expiryDurationInSeconds);
                expiry_time = Instant.now().plusSeconds(expiryDurationInSeconds - 300);
            } else {
                System.out.println("Token is good to use");
            }
        } catch (Exception e) {
            throw new RuntimeException("ABORT !!! Failed to get token");
        }
        return access_token;
    }

    private static Response renewToken() {
        HashMap<String, String> formParams = new HashMap<String, String>();
        formParams.put("client_id", ConfigLoader.getInstance().getClientId());
        formParams.put("client_secret", ConfigLoader.getInstance().getClientSecret());
        formParams.put("refresh_token", ConfigLoader.getInstance().getRefreshToken());
        formParams.put("grant_type", ConfigLoader.getInstance().getGrantType());
        Response response = RestResource.postAccount(formParams);
        System.out.println("Inside reniew token");
        if (response.statusCode() != 200) {
            throw new RuntimeException("ABORT !!! Renew Token Failed");
        }
        return response;
    }
}
