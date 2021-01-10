package com.ermolovsky.currency.clients;


import com.ermolovsky.currency.clients.models.CurrencyModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="CurrencyClient", url="https://openexchangerates.org/api/")
public interface CurrencyClient {
    @RequestMapping(method=RequestMethod.POST,value="/historical/{date}.json")

    public CurrencyModel getCurrency(@RequestParam(value="date") String date,
                                     @RequestParam(value="app_id") String appId,
                                     @RequestParam(value="symbols") String cur);
}


