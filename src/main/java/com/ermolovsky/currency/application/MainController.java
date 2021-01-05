//package com.ermolovsky.currency.application;
//
//import com.ermolovsky.currency.application.service.CurrencyService;
//import com.ermolovsky.currency.application.service.GifService;
//import com.ermolovsky.currency.clients.models.CurrencyModel;
//import com.ermolovsky.currency.clients.CurrencyClient;
//import com.ermolovsky.currency.clients.GifClient;
//import com.ermolovsky.currency.clients.models.GifModel;
//import com.ermolovsky.currency.clients.models.ImageModel;
//import com.ermolovsky.currency.clients.models.NodeGifModel;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class MainController {
//    public CurrencyService currencyService;
//    public CurrencyClient client;
//    public GifService gifService;
//
//
//    public MainController(CurrencyService currencyService, CurrencyClient client, GifService gifService) {
//        this.currencyService = currencyService;
//        this.client = client;
//        this.gifService = gifService;
//
//    }
//
//    @RequestMapping(method=RequestMethod.GET,value ="/currency")
//    public CurrencyModel getCurrency(@RequestParam(value="date") String date,
//                                    @Value("${myAppId}") String appId,
//                                    @RequestParam(value = "cur") String cur){
//        return client.getCurrency(date, appId, cur);
//    }
//    @RequestMapping(method = RequestMethod.GET,value = "/showdif")
//    public String getCurDif(){
//        if(currencyService.showDif())
//            return "true";
//        return "false";
//    }
//    @RequestMapping(method = RequestMethod.GET, value = "/gif")
//    public String getGif(){
//        GifModel gifModel = gifService.getGif();
//        //return gifModel.getData().get(0).getImages().get("images").get("original");
//        return gifModel.getData().get(0).getImages().getOriginal().get("url");
//    }
//}
