package com.ipl.xpto.trackingVehicles.util;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class ApiConsumer {

    private final String baseUrl;
    private final HttpClient client;

    public ApiConsumer(String baseUrl) {
        this.baseUrl = baseUrl;
        this.client = HttpClient.newHttpClient();
    }

    public CompletableFuture<HttpResponse<String>> get(String path) {
        URI uri = URI.create(baseUrl + path);
        System.out.print(uri);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
    }

}