package com.ermolovsky.currency.application.service;

import com.ermolovsky.currency.clients.GifClient;
import com.ermolovsky.currency.clients.models.GifModel;
import com.ermolovsky.currency.clients.models.ImageModel;
import com.ermolovsky.currency.clients.models.NodeGifModel;
import org.apache.commons.validator.routines.UrlValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.mockito.ArgumentMatchers;


import java.net.URL;
import java.util.ArrayList;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;

@SpringBootTest
class GifServiceTest {
    @Value("${application.properties.myGifId}")
    private String myGifId;
    @MockBean
    CurrencyService currencyService;
    @MockBean
    GifClient gifClient;
    @Autowired
    GifService gifService;

    @Test
    void testGetGif() {
        Mockito.when(currencyService.showDif()).thenReturn(true);

        GifModel gifModel = new GifModel();
        NodeGifModel nodeGifModel = new NodeGifModel();
        ImageModel imageModel = new ImageModel();
        TreeMap<String,String> imageMap = new TreeMap<>();
        imageMap.put("url","non-url-string");
        imageModel.setOriginal(imageMap);
        nodeGifModel.setImages(imageModel);
        ArrayList<NodeGifModel> dataList = new ArrayList<>();
        dataList.add(nodeGifModel);
        gifModel.setData(dataList);
        Mockito.when(gifClient.getGif(eq(myGifId),eq("rich"),eq(1),anyInt())).thenReturn(gifModel);
        //GifModel gifModel1 = gifService.getGif();
        String tmp = gifService.getGif().getData().get(0).getImages().getOriginal().get("url");
        UrlValidator urlValidator = new UrlValidator();
        Assertions.assertFalse(urlValidator.isValid(tmp));
        imageMap.put("url","https://developers.giphy.com/docs/api");
        tmp = gifService.getGif().getData().get(0).getImages().getOriginal().get("url");
        Assertions.assertTrue(urlValidator.isValid(tmp));
    }

}