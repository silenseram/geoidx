package com.ewmw.addr.config;

import com.ewmw.addr.config.services.GarConfig;
import com.ewmw.addr.config.services.ManticoresearchConfig;
import com.ewmw.addr.services.gar.types.HouseAddtypesService;
import com.ewmw.addr.services.gar.asobj.ObjectTypesService;
import com.ewmw.addr.services.gar.types.HouseTypesService;
import com.ewmw.addr.services.osm.api.NominatimApiClient;
import com.ewmw.addr.utils.FilesHelper;
import com.manticoresearch.client.ApiClient;
import com.manticoresearch.client.api.SearchApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.web.context.annotation.RequestScope;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

@Configuration
public class DiConfig {
    @Autowired
    GarConfig garConfig;

    @Autowired
    ManticoresearchConfig manticoresearchConfig;

    @Bean
    NominatimApiClient nominatimApiClient() {
        return new NominatimApiClient(manticoresearchConfig.getBaseUrl());
    }

    @Bean
    HouseTypesService houseTypesService() throws Exception {
        return new HouseTypesService(HouseTypesService.getHouseTypesFromXml(
                FilesHelper.getFirstFileByPrefix(garConfig.getTypesDirectory(), garConfig.getAsHouseTypesPrefix()).getPath()
        ));
    }

    @Bean
    HouseAddtypesService houseAddtypesService() throws Exception {
        return new HouseAddtypesService(HouseAddtypesService.getHouseTypesFromXml(
                    FilesHelper.getFirstFileByPrefix(garConfig.getTypesDirectory(), garConfig.getAsAddhouseTypesPrefix()).getPath()
        ));
    }

    @Bean
    ObjectTypesService objectTypesService() throws Exception {
        return ObjectTypesService.fromXml(
                FilesHelper.getFirstFileByPrefix(garConfig.getTypesDirectory(), garConfig.getAsAddrObjTypesPrefix()).getPath()
        );
    }

    @Bean
    SearchApi mntcrSearchApi() {
        return new SearchApi(new ApiClient());
    }
}
