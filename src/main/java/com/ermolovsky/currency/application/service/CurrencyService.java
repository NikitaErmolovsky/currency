package com.ermolovsky.currency.application.service;

import com.ermolovsky.currency.clients.models.CurrencyModel;
import com.ermolovsky.currency.clients.CurrencyClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class CurrencyService {
    private CurrencyClient currencyClient;
    private CurrencyModel todayModel;
    private CurrencyModel yesterdayModel;
    @Value("${myAppId}")
    private String appId;
    @Value("${currency}")
    private String currency;

    public CurrencyService(CurrencyClient currencyClient) {
        this.currencyClient = currencyClient;
    }

    public boolean showDif(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        todayModel = currencyClient.getCurrency(simpleDateFormat.format(date),appId,"RUB");
        long tmp = date.getTime();
        tmp = tmp - 86400000;
        Date yesterday = new Date(tmp);
        yesterdayModel = currencyClient.getCurrency(simpleDateFormat.format(yesterday),appId,"RUB");
        return todayModel.getRates().get(currency) - yesterdayModel.getRates().get(currency) > 0;
    }
}
