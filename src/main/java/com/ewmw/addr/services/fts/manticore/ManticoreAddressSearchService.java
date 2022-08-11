package com.ewmw.addr.services.fts.manticore;

import com.ewmw.addr.services.fts.manticore.response.docs.AddrSource;
import com.ewmw.addr.services.fts.manticore.response.docs.HitResult;
import com.manticoresearch.client.ApiException;
import com.manticoresearch.client.api.SearchApi;
import com.manticoresearch.client.model.SearchRequest;
import com.manticoresearch.client.model.SearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class ManticoreAddressSearchService {
    @Autowired
    SearchApi mntcrSearchApi;

    public List<HitResult> getAddresSuggestions(String input) {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.setIndex("addr_idx");
        searchRequest.setLimit(5);
        searchRequest.setQuery(
                new HashMap<String,Object>(){{
                    put("query_string", "\"" + input + "\"~3");
                }}
        );

        try {
            SearchResponse search = mntcrSearchApi.search(searchRequest);
            List<Object> hits = search.getHits().getHits();

            List<HitResult> hitResults = hits.stream().map((Object hit) -> {
                if (!(hit instanceof LinkedHashMap))
                    return null;
                LinkedHashMap<?, ?> hitMap = (LinkedHashMap<?, ?>) hit;

                LinkedHashMap sourceObj = (LinkedHashMap) hitMap.get("_source");

                AddrSource source = new AddrSource((String) sourceObj.get("addr"), (String) sourceObj.get("path"));
                HitResult hitResult = new HitResult((String) hitMap.get("_id"), (int) hitMap.get("_score"), source);

                return hitResult;
            }).toList();

            return hitResults;
        } catch (ApiException e) {
            e.printStackTrace();
        }

        return null;
    }
}
