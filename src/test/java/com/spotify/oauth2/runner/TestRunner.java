package com.spotify.oauth2.runner;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = {"src/test/resources/features"},
            glue = {"src/test/java/com.spotify.oauth2/tests"})
public class TestRunner {
}
