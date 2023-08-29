package com.spotify.oauth2.tests;

import com.spotify.oauth2.api.StatusCode;
import com.spotify.oauth2.api.applicationApi.PlaylistApi;
import com.spotify.oauth2.pojo.Error;
import com.spotify.oauth2.pojo.Playlist;
import com.spotify.oauth2.utils.DataLoader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.spotify.oauth2.utils.FakerUtils.generateDescription;
import static com.spotify.oauth2.utils.FakerUtils.generateName;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Epic("Spotify Oauth 2.0")
@Feature("Playlist API")
public class PlaylistTests extends BaseTest {

    public Playlist requestPlaylist;
    public Response response;

    public PlaylistTests(){}

    @Step
    public Playlist playlistBuilder(String name, String description, boolean _public) {
        return Playlist.builder().
                name(name).description(description)._public(_public).build();
    }

    @Step
    public void assertPlaylistEqual(Playlist responsePlaylist, Playlist requestPlaylist) {
        assertThat(responsePlaylist.getName(), equalTo(requestPlaylist.getName()));
        assertThat(responsePlaylist.getDescription(), equalTo(requestPlaylist.getDescription()));
        assertThat(responsePlaylist.get_public(), equalTo(requestPlaylist.get_public()));
    }

    @Step
    public void assertStatusCode(int actualStatusCode, StatusCode statuscode) {
        assertThat(actualStatusCode, equalTo(statuscode.code));
    }

    @Step
    public void assertError(Error responseErr, StatusCode statuscode) {
        assertThat(responseErr.getError().getStatus(), equalTo(statuscode.code));
        assertThat(responseErr.getError().getMessage(), equalTo(statuscode.msg));
    }

    @Story("Create a playlist story")
    @Link("https://example.org")
    @Link(name = "allure", type = "mylink")
    @TmsLink("test-1")
    @Issue("1234567")
    @Description("Here I am creating the playlist")
    @Test(description = "Should be able to create playlist")
    @Story("Create a playlist story")
    @Given("We have created the post body")
    public void ShouldBeAbleToCreatePlaylist() {
        requestPlaylist = playlistBuilder(generateName(), generateDescription(), false);
        response = PlaylistApi.post(requestPlaylist);
        System.out.println("Build the body" + response);
        assertStatusCode(response.statusCode(), StatusCode.CODE_201);
        assertPlaylistEqual(response.as(Playlist.class), requestPlaylist);
        System.out.println("Verified the status code");
    }

    @When("We get the response")
    public void getTheResponseForPlaylist() {
        System.out.println("response");
    }

    @Then("the values should match")
    public void verifyTheAssertForPlaylist() {
        System.out.println("assert");
    }


    @Story("Create a playlist story")
    @Link("https://example.org")
    @Link(name = "allure", type = "mylink")
    @TmsLink("test-1")
    @Issue("1234567")
    @Description("Here I am creating the playlist")
    @Test(description = "Should be able to create playlist")
    public void ShouldBeAbleToCreatePlaylistWithValidDetails() {
        requestPlaylist = playlistBuilder(generateName(), generateDescription(), false);
        response = PlaylistApi.post(requestPlaylist);
        assertStatusCode(response.statusCode(), StatusCode.CODE_201);
        assertPlaylistEqual(response.as(Playlist.class), requestPlaylist);
    }

    @Story("Create a playlist story")
    @Test
    public void ShouldBeAbleToGetPlaylist() {
        Playlist requestPlaylist = playlistBuilder("Updated playlist name", "Updated playlist description", false);
        Response response = PlaylistApi.get(DataLoader.getInstance().getGetPlaylistId());
        assertStatusCode(response.statusCode(), StatusCode.CODE_200);
        assertPlaylistEqual(response.as(Playlist.class), requestPlaylist);
    }

    @Story("Create a playlist story")
    @Test
    public void ShouldBeAbleToUpdatePlaylist() {
        // Note please disable "expectContentType(ContentType.JSON)." in response responseSpecBuilder for executing out request
        Playlist requestPlaylist = playlistBuilder("Updated playlist name", "Updated playlist description", false);
        Response response = PlaylistApi.update(DataLoader.getInstance().getUpdatePlaylistId(), requestPlaylist);
        assertStatusCode(response.statusCode(), StatusCode.CODE_200);
    }

    @Test
    public void ShouldNotBeAbleToCreatePlaylistWithName() {
        Playlist requestPlaylist = playlistBuilder("", generateDescription(), false);
        Response response = PlaylistApi.post(requestPlaylist);
        assertStatusCode(response.statusCode(), StatusCode.CODE_400);
        assertError(response.as(Error.class), StatusCode.CODE_400);
    }

    @Test
    public void ShouldNotBeAbleToCreatePlaylistWithExpiredToken() {
        String invalid_token = "12345";
        Playlist requestPlaylist = playlistBuilder(generateName(), generateDescription(), false);
        Response response = PlaylistApi.post(invalid_token, requestPlaylist);
        assertStatusCode(response.statusCode(), StatusCode.CODE_401);
        assertError(response.as(Error.class), StatusCode.CODE_401);
    }
}