package com.ermolovsky.currency.application;

import com.ermolovsky.currency.application.service.CurrencyService;
import com.ermolovsky.currency.curclient.CurrencyModel;
import com.ermolovsky.currency.curclient.CurrencyClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    public CurrencyService currencyService;
    public CurrencyClient client;
    public MainController(CurrencyClient client, CurrencyService currencyService) {
        this.client = client;
        this.currencyService = currencyService;
    }


    @RequestMapping(method=RequestMethod.GET,value ="/currency")
    public CurrencyModel getCurrency(@RequestParam(value="date") String date,
                                    @Value("${myAppId}") String appId,
                                    @RequestParam(value = "cur") String cur){
        return client.getCurrency(date, appId, cur);
    }
    @RequestMapping(method = RequestMethod.GET,value = "/showdif")
    public String getCurDif(){
        if(currencyService.showDif())
            return "true";
        return "false";
    }
}
