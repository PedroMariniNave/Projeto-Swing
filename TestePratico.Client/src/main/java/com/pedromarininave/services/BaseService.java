package com.pedromarininave.services;

import java.net.http.HttpClient;

public abstract class BaseService<T> extends BaseServiceAPI {

    public BaseService(String uri) {
        super(HttpClient.newHttpClient(), uri);
    }
}