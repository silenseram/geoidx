package com.ewmw.addr.services;

import lombok.Getter;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiClient {
    @Getter
    protected String baseUrl;

    @Getter
    protected HttpClient client;

    public ApiClient(String baseUrl) {
        client = HttpClient.newHttpClient();

        this.baseUrl = baseUrl;
    }

    protected HttpResponse<String> sendRequest(String type, String uri, String body) throws IOException, InterruptedException {
        HttpRequest.Builder builder = HttpRequest.newBuilder();
        if (type.equals("GET"))
            builder.GET();

        if (type.equals("POST"))
            builder.POST(HttpRequest.BodyPublishers.ofString(body));

        HttpRequest request = builder
                    .uri(URI.create(baseUrl + uri))
//                .header("Authorization", token)
//                .header("Content-Type", "application/json")
                .build();

        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
