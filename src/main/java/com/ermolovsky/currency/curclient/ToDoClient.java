package com.ermolovsky.currency.curclient;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@FeignClient(name="TodoClient", url="https://openexchangerates.org/api/")
public interface ToDoClient {
    @RequestMapping(method=RequestMethod.POST,value="/historical/{date}.json")

    public CurrencyModel getCurrency(@RequestParam(value="date") Date date,
                                           @RequestParam(value="app_id", name="myAppId") String appId,
                                           @RequestParam(value="symbols", name="symbolsCur") String cur);
}


