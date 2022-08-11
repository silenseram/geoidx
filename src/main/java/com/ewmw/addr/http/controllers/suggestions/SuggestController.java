package com.ewmw.addr.http.controllers.suggestions;

import com.ewmw.addr.services.address.AddressService;
import com.ewmw.addr.services.fts.manticore.ManticoreAddressSearchService;
import com.ewmw.addr.services.fts.manticore.response.docs.HitResult;
import com.ewmw.addr.services.osm.converter.Gar2OsmSearchService;
import org.sphx.api.SphinxException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/suggest")
public class SuggestController {

    @Autowired
    ManticoreAddressSearchService manticoreAddressSearchService;

    @Autowired
    Gar2OsmSearchService gar2OsmSearchService;

    @Autowired
    AddressService addressService;

    @GetMapping
    public Map<String, Object> findAddresses(@RequestParam(name = "q") String query) throws SphinxException {
        Map<String, Object> response = new LinkedHashMap<>();

        List<Map> suggestions = new ArrayList<>();


        List<HitResult> addresSuggestions = manticoreAddressSearchService.getAddresSuggestions(query);
//        List<String> paths = sphinxMatcher.findAddresses(query);

        addresSuggestions.forEach(addresSuggestion -> {
            suggestions.add(addressService.getDetailedAddressForIds(addresSuggestion.getSource().getPath()));
        });

//        for (String path : paths) {
//            Map<String, Object> detailedAddressForIds = addressService.getDetailedAddressForIds(path);
//
//            suggestions.add(detailedAddressForIds);
//        }

        response.put("suggestions", suggestions);

        return response;
    }
}
