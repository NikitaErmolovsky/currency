package com.ermolovsky.currency.clients.models;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.TreeMap;

public class CurrencyModel {
    @Getter @Setter private String disclaimer;
    @Getter @Setter private String license;
    @Getter @Setter private Timestamp timestamp;
    @Getter @Setter private String base;
    @Getter @Setter private TreeMap<String,Double> rates;


}
