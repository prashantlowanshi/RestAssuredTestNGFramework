package com.spotify.oauth2.api.applicationApi;

import com.spotify.oauth2.api.RestResource;
import com.spotify.oauth2.pojo.SaveSettings;
import com.spotify.oauth2.utils.UriLoader;
import io.restassured.response.Response;

import static com.spotify.oauth2.api.Route.BASE_SAVE;
import static com.spotify.oauth2.api.Route.BASE_SETTINGS;

public class Shift4ShopApi {

    public static Response post(SaveSettings shift4ShopRequest)
    {
        return RestResource.post(BASE_SETTINGS + BASE_SAVE, UriLoader.getInstance().getBaseCisToken(), shift4ShopRequest);
    }
}
