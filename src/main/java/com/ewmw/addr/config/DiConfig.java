package com.ewmw.addr.config;

import com.ewmw.addr.services.gar.types.HouseAddtypesService;
import com.ewmw.addr.services.gar.asobj.ObjectTypesService;
import com.ewmw.addr.services.gar.types.HouseTypesService;
import com.ewmw.addr.services.osm.api.NominatimApiClient;
import com.manticoresearch.client.ApiClient;
import com.manticoresearch.client.api.SearchApi;
import org.sphx.api.SphinxClient;
import org.sphx.api.SphinxException;
import org.springframework.context.annotation.*;
import org.springframework.web.context.annotation.RequestScope;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

@Configuration
public class DiConfig {
    @Bean
    NominatimApiClient nominatimApiClient() {
        return new NominatimApiClient("http://localhost:8181");
    }

    @Bean
    HouseTypesService houseTypesService() throws ParserConfigurationException, IOException, SAXException {
        return new HouseTypesService(HouseTypesService.getHouseTypesFromXml("gar_ext\\types\\AS_HOUSE_TYPES_20220324_1c2dd4ae-e693-40e7-ab88-74118f98ab6e.XML"));
    }

    @Bean
    HouseAddtypesService houseAddtypesService() throws ParserConfigurationException, IOException, SAXException {
        return new HouseAddtypesService(HouseAddtypesService.getHouseTypesFromXml("gar_ext\\types\\AS_ADDHOUSE_TYPES_20220324_fcb40e02-edd8-4d82-bc8d-61cf99d370e3.XML"));
    }

    @Bean
    ObjectTypesService objectTypesService() throws ParserConfigurationException, IOException, SAXException {
        return ObjectTypesService.fromXml("gar_ext\\types\\AS_ADDR_OBJ_TYPES_20220324_3bf1c02b-f2ec-4942-8813-b6a024010655.XML");
    }

    @Bean
    SearchApi mntcrSearchApi() {
        return new SearchApi(new ApiClient());
    }
}
