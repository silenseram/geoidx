package com.ewmw.addr.services.osm.processor;

import org.springframework.stereotype.Service;

@Service
public class GarAddrTypesMapper {
    public String getOsmName(int garLevel) {
        switch (garLevel) {
            case 1:
                return "region";
            case 2:
                return "administrative_area";
            case 3:
                return "municipal_area";
            case 4:
                return "settlement";
            case 5:
                return "city";
            case 6:
                return "locality";
            case 7:
                return "block";
            case 8:
                return "street";
            case 14:
                return "city_area";
        }

        return null;
    }
}
