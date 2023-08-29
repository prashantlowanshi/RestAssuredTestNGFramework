package com.spotify.oauth2.tests;

import com.spotify.oauth2.api.StatusCode;
import com.spotify.oauth2.api.applicationApi.Shift4ShopApi;
import com.spotify.oauth2.pojo.Error;
import com.spotify.oauth2.pojo.SaveSettings;
import io.cucumber.java.en.*;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.spotify.oauth2.utils.Shift4ShopUtils.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Epic("Shift4Shop")
@Feature("Shift4Shop SaveSettings")
public class ShiftForShopTests extends BaseTest{

    public SaveSettings shift4ShopRequest;
    public Response response;

    public ShiftForShopTests(){}

    @Step
    public SaveSettings saveSettingsBuilder(Integer subscriberId, Integer profileId, Integer channelType,
                                                 Integer integrationTypeId, String storeName, String accessToken, String connectionURL,
                                                 String downloadStartDate) {
        return SaveSettings.builder().
                subscriberId(subscriberId).profileId(profileId).channelType(channelType).
                integrationTypeId(integrationTypeId).storeName(storeName).accessToken(accessToken).
                connectionURL(connectionURL).downloadStartDate(downloadStartDate).build();
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

    @Story("Should be able to save the settings")
    @Link("https://example.org")
    @Link(name = "allure", type = "mylink")
    @TmsLink("test-1")
    @Issue("AP-6058")
    @Description("Here I am saving the valid settings")
    //@Test(description = "Should be able to save the settings")
    @Story("Save the settings for Shift4Shop")
    @Given("We have added the json body for post request")
    public void ShouldBeAbleToSaveSettingsForShift4Shop() {
        shift4ShopRequest = saveSettingsBuilder(getSubscriberId(),getProfileId(),getChannelType(), getIntegrationTypeId(),
                getStoreName(),  getAccessToken(), getConnectionURL(), getDownloadStartDate());
    }

    @When("Request to save the setting is done")
    public void getTheResponseAfterHittingTheRequest() {
        response = Shift4ShopApi.post(shift4ShopRequest);
    }

    @Then("Correct status code should be displayed")
    public void verifyTheAssertAfterGettingTheResponse() {
        assertStatusCode(response.statusCode(), StatusCode.CODE_200);
    }
}
