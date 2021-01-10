package com.ermolovsky.currency.application.service;

import com.ermolovsky.currency.clients.CurrencyClient;
import com.ermolovsky.currency.clients.models.CurrencyModel;
import org.junit.jupiter.api.Assertions;
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
@SpringBootTest
class CurrencyServiceTest {
    @Value("${myAppId}")
    private String appId;
    @Value("${currency}")
    private String currency;
    @MockBean
    CurrencyClient currencyClient;
    @Autowired
    CurrencyService currencyService;

    @Test
    void testShowDif() {
        CurrencyModel todayModel = new CurrencyModel();
        CurrencyModel yesterdayModel = new CurrencyModel();
        TreeMap<String,Double> model1 = new TreeMap<>();
        model1.put(currency,2.0);
        todayModel.setRates(model1);
        TreeMap<String,Double> model2 = new TreeMap<>();
        model2.put(currency,1.0);
        yesterdayModel.setRates(model2);
        Date today = new Date();
        long tmp = today.getTime();
        tmp = tmp - 86400000;
        Date yesterday = new Date(tmp);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Mockito.when(currencyClient.getCurrency(simpleDateFormat.format(today),appId,currency)).thenReturn(todayModel);
        Mockito.when(currencyClient.getCurrency(simpleDateFormat.format(yesterday),appId,currency)).thenReturn(yesterdayModel);
        Assertions.assertTrue(currencyService.showDif());

        Mockito.when(currencyClient.getCurrency(simpleDateFormat.format(today),appId,currency)).thenReturn(yesterdayModel);
        Mockito.when(currencyClient.getCurrency(simpleDateFormat.format(yesterday),appId,currency)).thenReturn(todayModel);
        Assertions.assertFalse(currencyService.showDif());

    }
}