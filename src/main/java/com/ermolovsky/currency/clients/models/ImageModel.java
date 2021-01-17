package com.ermolovsky.currency.clients.models;

import lombok.Getter;
import lombok.Setter;

import java.util.TreeMap;

public class ImageModel {
    @Getter @Setter private TreeMap<String,String> original;
}
