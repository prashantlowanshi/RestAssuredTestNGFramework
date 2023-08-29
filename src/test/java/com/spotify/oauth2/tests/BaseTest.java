package com.spotify.oauth2.tests;

import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.lang.reflect.Method;

public class BaseTest
{
    public Response response;

    @BeforeMethod
    public void beforemethod(Method m)
    {
      System.out.println("STARTING TEST : " + m.getName());
      System.out.println("THREAD ID : " + Thread.currentThread().getId());
    }
}
