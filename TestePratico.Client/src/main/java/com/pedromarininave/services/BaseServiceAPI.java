package com.pedromarininave.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;

public abstract class BaseServiceAPI {
    private final HttpClient httpClient;
    private final String uri;
    private final ObjectMapper objectMapper;

    public BaseServiceAPI(HttpClient httpClient, String uri) {
        this.httpClient = httpClient;
        this.uri = uri;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }

    protected CompletableFuture<String> getAsync(Object... parameters) {
        String url = createUrl(parameters);
        HttpRequest request = HttpRequest.newBuilder()
                                         .uri(URI.create(url))
                                         .GET()
                                         .build();

        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                         .thenApply(HttpResponse::body);
    }

    protected CompletableFuture<String> postAsync(Object object, Object... parameters) {
        try {
            String url = createUrl(parameters);
            String json = objectMapper.writeValueAsString(object);
            HttpRequest request = HttpRequest.newBuilder()
                                             .uri(URI.create(url))
                                             .header("Content-Type", "application/json")
                                             .POST(HttpRequest.BodyPublishers.ofString(json, StandardCharsets.UTF_8))
                                             .build();

            return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                             .thenApply(HttpResponse::body);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    protected CompletableFuture<String> putAsync(Object object, Object... parameters) {
        try {
            String url = createUrl(parameters);
            String json = objectMapper.writeValueAsString(object);
            HttpRequest request = HttpRequest.newBuilder()
                                             .uri(URI.create(url))
                                             .header("Content-Type", "application/json")
                                             .PUT(HttpRequest.BodyPublishers.ofString(json, StandardCharsets.UTF_8))
                                             .build();

            return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                             .thenApply(HttpResponse::body);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    protected CompletableFuture<String> deleteAsync(Object... parameters) {
        String url = createUrl(parameters);
        HttpRequest request = HttpRequest.newBuilder()
                                         .uri(URI.create(url))
                                         .DELETE()
                                         .build();

        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                         .thenApply(HttpResponse::body);
    }

    private String createUrl(Object... parameters) {
        StringBuilder stringBuilder = new StringBuilder().append("http://localhost:8080/")
                                                         .append(uri);

        for (Object parameter : parameters) {
            stringBuilder.append("/")
                         .append(parameter);
        }

        return stringBuilder.toString();
    }
}