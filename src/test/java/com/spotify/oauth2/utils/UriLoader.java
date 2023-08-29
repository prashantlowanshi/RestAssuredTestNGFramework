package com.spotify.oauth2.utils;
import java.util.Properties;

public class UriLoader {

    private final Properties properties;
    private static UriLoader uriLoader;

    private UriLoader(){
        properties = PropertyUtils.propertyLoader("src/test/resources/base.properties");
    }

    public static UriLoader getInstance(){
        if(uriLoader == null){
            uriLoader = new UriLoader();
        }
        return uriLoader;
    }

    public String getBaseUri(){
        String prop = properties.getProperty("BASE_URI");
        if(prop != null) return prop;
        else throw new RuntimeException("property BASE_URI is not specified in the data.properties file");
    }

    public String getAccountUri(){
        String prop = properties.getProperty("ACCOUNT_BASE_URI");
        if(prop != null) return prop;
        else throw new RuntimeException("property BASE_URI is not specified in the data.properties file");
    }
    public String getBaseUriCis(){
        String prop = properties.getProperty("BASE_URI_CIS");
        if(prop != null) return prop;
        else throw new RuntimeException("property BASE_URI is not specified in the data.properties file");
    }

    public String getBaseCisToken(){
        String prop = properties.getProperty("BASE_CIS_TOKEN");
        if(prop != null) return prop;
        else throw new RuntimeException("property BASE_URI is not specified in the data.properties file");
    }


}
