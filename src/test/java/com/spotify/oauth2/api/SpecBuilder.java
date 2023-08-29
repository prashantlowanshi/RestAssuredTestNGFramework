package com.spotify.oauth2.api;
import com.spotify.oauth2.utils.DataLoader;
import com.spotify.oauth2.utils.UriLoader;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static com.spotify.oauth2.api.Route.BASE_PATH;
import static com.spotify.oauth2.api.Route.BASE_PATH_CIS;

public class SpecBuilder {
    public static RequestSpecification getRequestSpec() {

        return new RequestSpecBuilder().
                setBaseUri(UriLoader.getInstance().getBaseUriCis()).
                //setBaseUri(UriLoader.getInstance().getBaseUri()).
                //setBaseUri("https://api.spotify.com").
                // The BASE_URI and setBaseUri are added in configurations folder
                setBasePath(BASE_PATH_CIS).
                //setBasePath(BASE_PATH).
                setContentType(ContentType.JSON).
                addFilter(new AllureRestAssured()).
                log(LogDetail.ALL).
                build();
    }

    public static RequestSpecification getAccountRequestSpec()
    {
        return new RequestSpecBuilder().
                setBaseUri(UriLoader.getInstance().getAccountUri()).
                //setBaseUri("https://accounts.spotify.com/").
                setContentType(ContentType.URLENC).
                addFilter(new AllureRestAssured()).
                log(LogDetail.ALL).
                build();
    }

    public static ResponseSpecification getResponseSpec() {
        return new ResponseSpecBuilder().
                log(LogDetail.ALL).
                build();
    }
}

