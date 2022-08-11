package com.ewmw.addr.services.gar.params;

import com.ewmw.addr.models.gar.GarHouse;
import org.springframework.stereotype.Service;

@Service
public class HouseParamsTypesService {

    public GarHouse setHouseParam(String paramId, String paramValue, GarHouse house) {
        switch (paramId) {
            case "1":
                house.setIfnsFl(paramValue);
                break;
            case "2":
                house.setIfnsUl(paramValue);
                break;
            case "3":
                house.setTerIfnsFl(paramValue);
                break;
            case "4":
                house.setTerIfnsUl(paramValue);
                break;
            case "5":
                house.setPostalCode(paramValue);
                break;
            case "6":
                house.setOkato(paramValue);
                break;
            case "7":
                house.setOktmo(paramValue);
                break;
            case "8":
                house.setKadnum(paramValue);
                break;
            case "9":
//                house.setKladrCode(paramValue);
                break;
            case "10":
                house.setKladrCode(paramValue);
                break;
            case "11":
//                house.setIfnsFl(paramValue);
                break;
            case "12":
                house.setRegionCode(paramValue);
                break;
            case "13":
                house.setReestrNum(paramValue);
                break;
            case "14":
                house.setDivisionType(paramValue);
                break;
            case "15":
//                house.setIfnsFl(paramValue);
                break;
            case "16":
                house.setOfficialName(paramValue);
                break;
            case "17":
                house.setPostStatus(paramValue.equals("1"));
                break;

        }

        return house;
    }
}
