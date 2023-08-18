package com.spotify.oauth2.api.applicationApi;

import com.spotify.oauth2.api.RestResource;
import com.spotify.oauth2.pojo.Error;
import com.spotify.oauth2.pojo.Playlist;
import com.spotify.oauth2.utils.ConfigLoader;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static com.spotify.oauth2.api.Route.PLAYLISTS;
import static com.spotify.oauth2.api.Route.USERS;
import static com.spotify.oauth2.api.SpecBuilder.getRequestSpec;
import static com.spotify.oauth2.api.SpecBuilder.getResponseSpec;
import static com.spotify.oauth2.api.TokenManager.getToken;
import static io.restassured.RestAssured.given;

public class PlaylistApi
{
   @Step
   public static Response post(Playlist requestPlaylist)
   {
      return RestResource.post(USERS + "/" + ConfigLoader.getInstance().getUser() + PLAYLISTS, getToken(), requestPlaylist);
   }
   public static Response post(String token, Playlist requestPlaylist)
   {
      return RestResource.post(USERS + "/" + ConfigLoader.getInstance().getUser() + PLAYLISTS, token, requestPlaylist);
   }
   public static Response get(String PlaylistId)
   {
      return RestResource.get(PLAYLISTS + "/" + PlaylistId, getToken());
   }
   public static Response update(String PlaylistId, Playlist requestPlaylist)
   {
      return RestResource.update(PLAYLISTS + "/" + PlaylistId, getToken(), requestPlaylist);
   }
}
