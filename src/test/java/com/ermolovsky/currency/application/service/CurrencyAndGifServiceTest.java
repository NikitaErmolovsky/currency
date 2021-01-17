package com.ermolovsky.currency.application.service;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.apache.commons.validator.routines.UrlValidator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@SpringBootTest(properties = {"curUrl = http://localhost:8090/currency",
        "gifUrl = http://localhost:8090/gif"})
class CurrencyAndGifServiceTest {
    @Value("${application.properties.myAppId}")
    private String appId;
    @Value("${application.properties.currency}")
    private String currency;
    @Value("${curUrl}")
    private String curUrl;

    WireMockServer wireMockServer;

    @BeforeEach
    public void setup () {
        wireMockServer = new WireMockServer(8090);
        wireMockServer.start();
        setupStub();
    }

    @AfterEach
    public void teardown () {
        wireMockServer.stop();
    }

    public void setupStub() {
        Date today = new Date();
        long tmp = today.getTime();
        tmp = tmp - 86400000;
        Date yesterday = new Date(tmp);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String todayUrl = "/currency.*date="+simpleDateFormat.format(today)+".*";
        String yesterdayUrl = "/currency.*date="+simpleDateFormat.format(yesterday)+".*";
        wireMockServer.stubFor(post(urlMatching(todayUrl))
                .willReturn(aResponse().withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBodyFile("json/today_currency_response.json")));
        wireMockServer.stubFor(post(urlMatching(yesterdayUrl))
                .willReturn(aResponse().withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBodyFile("json/yesterday_currency_response.json")));
    }

    @Autowired
    CurrencyService currencyService;
    @Autowired
    GifService gifService;

    @Test
    void testShowDif() {
        Assertions.assertFalse(currencyService.showDif());
    }
    @Test
    void testGetGif(){
        wireMockServer.stubFor(get(urlMatching("/gif.*")).
                willReturn(aResponse().withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBodyFile("json/gif_response_non_url.json")));
        String tmp = gifService.getGif().getData().get(0).getImages().getOriginal().get("url");
        UrlValidator urlValidator = new UrlValidator();
        Assertions.assertFalse(urlValidator.isValid(tmp));

        wireMockServer.stubFor(get(urlMatching("/gif.*")).
                willReturn(aResponse().withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBodyFile("json/gif_response_url.json")));
        tmp = gifService.getGif().getData().get(0).getImages().getOriginal().get("url");
        Assertions.assertTrue(urlValidator.isValid(tmp));

    }
}