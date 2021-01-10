package com.ermolovsky.currency.application.service;

import com.ermolovsky.currency.clients.GifClient;
import com.ermolovsky.currency.clients.models.GifModel;
import com.ermolovsky.currency.clients.models.NodeGifModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;


@Service
public class GifService {
    private GifClient gifClient;
    private CurrencyService currencyService;
    private GifModel gifModel;
    private NodeGifModel nodeGifModel;
    @Value("${myGifId}")
    private String myGifId;
    private String query;

    public GifService(GifClient gifClient, CurrencyService currencyService) {
        this.currencyService = currencyService;
        this.gifClient = gifClient;
    }
    public GifModel getGif(){
        if(currencyService.showDif())
            query = "rich";
        else query = "broke";
        Date date = new Date();
        Random random = new Random(date.getTime());
        int number = random.nextInt(100);
        gifModel = gifClient.getGif(myGifId,query,1,number);
        return gifModel;
    }
}
