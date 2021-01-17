package com.ermolovsky.currency.clients;

import com.ermolovsky.currency.clients.models.GifModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="GifClient", url="${gifUrl}")
public interface GifClient {
    @RequestMapping(method = RequestMethod.GET, value = "")
    public GifModel getGif(@RequestParam(value = "api_key") String appId,
                           @RequestParam(value = "q") String query,
                           @RequestParam(value = "limit", defaultValue ="1") int value,
                           @RequestParam(value = "offset") int number);

}
