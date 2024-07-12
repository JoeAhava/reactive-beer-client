package com.yigbu.reactive_web_client.config;

import org.springframework.web.util.UriBuilder;

import java.net.URI;
import java.util.function.Function;

public class WebClientProperties {
    public static final String BASE_URL = "http://api.springframework.guru";
    public static final String BEER_PATH_V1 = "/api/v1/beer";
    public static final String BEER_PATH_V1_GET_BY_ID = "/api/v1/beer/{uuid}";
    public static final String BEER_PATH_V1_UPC = "/api/v1/beerUpc/{upc}";
}
