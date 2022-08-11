package com.ewmw.addr.services.osm.api;

import com.ewmw.addr.services.ApiClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLEncoder;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NominatimApiClient extends ApiClient {

    public NominatimApiClient(String baseUrl) {
        super(baseUrl);
    }

    public Map<String, Object> search(Map<String, String> searchParams) {
        ArrayList<String> query = new ArrayList<>();
        HashMap<String, Object> responseParams = new HashMap<>();

        searchParams.keySet().forEach(key -> {
            String encodedValue = URLEncoder.encode(searchParams.get(key), StandardCharsets.UTF_8);
            query.add(key + "=" + encodedValue);
        });

//        query.add("city=" + searchParams.get("city"));
//        query.add("street=" + searchParams.get("street"));

        String queryString = String.join("&", query);

        try {
            HttpResponse<String> get = sendRequest("GET", "/search.php/?" + queryString, "");
            Class<? extends ArrayList> responseClazz = new ArrayList<Map<String, Object>>().getClass();

            ArrayList<Map<String, Object>> arrayList = new ObjectMapper().readValue(get.body(), responseClazz);

            System.out.println("/search.php/" + queryString);
            System.out.println(get.body());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }


        return responseParams;
    }
}
