package com.ermolovsky.currency.clients.models;

import java.util.ArrayList;
import java.util.TreeMap;

public class GifModel {
    private ArrayList<NodeGifModel> data;
    private TreeMap<String,String> meta;

    public ArrayList<NodeGifModel> getData() {
        return data;
    }

    public void setData(ArrayList<NodeGifModel> data) {
        this.data = data;
    }

    public TreeMap<String, String> getMeta() {
        return meta;
    }

    public void setMeta(TreeMap<String, String> meta) {
        this.meta = meta;
    }
}
