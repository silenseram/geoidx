package com.ewmw.addr.services.osm.converter;

import com.ewmw.addr.models.gar.GarAddr;
import com.ewmw.addr.models.gar.GarHouse;
import com.ewmw.addr.services.gar.types.HouseType;
import com.ewmw.addr.services.gar.types.HouseTypesService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Gar2OsmSearchService {
    @Autowired
    HouseTypesService houseTypesService;

    public HashMap<String, String> getSearchParams(Map<String, Object> addressDetails) {
        HashMap<String, String> searchParams = new HashMap<>();

        String street = "";
        String city = "";

        searchParams.put("addressdetails", "1");
        searchParams.put("extratags", "1");
        searchParams.put("namedetails", "1");
        searchParams.put("dedupe", "1");

        String cityName = extractDetail(addressDetails, "city_name");
        String regionName = extractDetail(addressDetails, "region_name");

        city = regionName;

        if (cityName != null)
            city += " " + cityName;

        String houseNum = extractDetail(addressDetails, "house_num");
        String streetName = extractDetail(addressDetails, "street_name");

        String houseAddtype1 = extractDetail(addressDetails, "house_addtype1");
        String houseAddnum1 = extractDetail(addressDetails, "house_addnum1");

        HouseType houseType = houseTypesService.getHouseType(houseAddtype1);

        String shortAddTypeName = "";

        if (houseType != null)
            shortAddTypeName = houseType.getShortName() + houseAddnum1;

        street = shortAddTypeName + " " + houseNum + " " + streetName;

        searchParams.put("city", city);
        searchParams.put("street", street);

        return searchParams;
    }

    private String extractDetail(Map<String, Object> addressDetails, String detailName) {
        Object detail = addressDetails.get(detailName);

        if (detail instanceof String)
            return (String) detail;

        return null;
    }
}
