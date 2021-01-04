package com.ermolovsky.currency.gifclient;

import com.ermolovsky.currency.gifclient.model.GifModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="CurrencyClient", url="https://api.giphy.com/v1/gifs/search")
public interface GifClient {
    @RequestMapping(method = RequestMethod.POST, value = "/gif")
    public GifModel getGif(@RequestParam(value = "app_id") String appId,
                           @RequestParam(value = "query") String query,
                           @RequestParam(value = "randomNumber") int number);

}
