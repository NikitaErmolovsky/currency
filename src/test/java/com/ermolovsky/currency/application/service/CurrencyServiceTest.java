package com.ermolovsky.currency.application.service;

import com.ermolovsky.currency.clients.CurrencyClient;
import com.ermolovsky.currency.clients.models.CurrencyModel;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.checkerframework.checker.fenum.qual.AwtAlphaCompositingRule;
import org.apache.commons.digester.annotations.CreationRule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;
import static com.github.tomakehurst.wiremock.client.WireMock.*;

@SpringBootTest
class CurrencyServiceTest {
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
        wireMockServer.stubFor(post(anyUrl())
                .willReturn(aResponse().withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBodyFile("json/currency_response.json")));
    }

    @Autowired
    CurrencyService currencyService;

    @Test
    void testShowDif() {
//        Date today = new Date();
//        long tmp = today.getTime();
//        tmp = tmp - 86400000;
//        Date yesterday = new Date(tmp);
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");



        Assertions.assertTrue(currencyService.showDif());

    }
}