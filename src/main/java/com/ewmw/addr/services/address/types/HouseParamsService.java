package com.ewmw.addr.services.address.types;

import com.ewmw.addr.models.gar.GarHouse;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class HouseParamsService {
    public HashMap<String, Object> getHouseParams(GarHouse garHouse) {
        HashMap<String, Object> houseParams = new HashMap<>();

        houseParams.put("house_num", garHouse.getHouseNum());
        houseParams.put("house_guid", garHouse.getObjectGuid());
        houseParams.put("house_addnum1", garHouse.getAddNum1());
        houseParams.put("house_addnum2", garHouse.getAddNum2());
        houseParams.put("house_addtype1", garHouse.getAddType1());
        houseParams.put("house_addtype2", garHouse.getAddType2());
        houseParams.put("okato", garHouse.getOkato());
        houseParams.put("oktmo", garHouse.getOktmo());
        houseParams.put("postal_code", garHouse.getPostalCode());
        houseParams.put("kadnum", garHouse.getKadnum());
        houseParams.put("ifns_fl", garHouse.getIfnsFl());
        houseParams.put("ifns_ul", garHouse.getIfnsUl());

        return houseParams;
    }
}
