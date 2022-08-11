package com.ewmw.addr.services.address;

import com.ewmw.addr.models.gar.GarAddr;
import com.ewmw.addr.models.reposotories.GarAddrRepository;
import com.ewmw.addr.models.reposotories.GarHouseRepository;
import com.ewmw.addr.services.address.types.AddrTypesManager;
import com.ewmw.addr.services.gar.hierarchy.HouseTypeDoesNotExistsException;
import com.ewmw.addr.models.gar.GarHouse;
import com.ewmw.addr.services.address.types.HouseParamsService;
import com.ewmw.addr.services.gar.names.AddressNamesService;
import com.ewmw.addr.services.gar.names.HouseNamesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AddressService {
    @Autowired
    GarHouseRepository garHouseRepository;

    @Autowired
    GarAddrRepository garAddrRepository;

    @Autowired
    AddrTypesManager addrTypesManager;

    @Autowired
    HouseParamsService houseParamsService;

    @Autowired
    AddressNamesService addressNamesService;

    @Autowired
    HouseNamesService houseNamesService;


    public Map<String, Object> getDetailedAddressForIds(String path) {
        List<Long> ids = new ArrayList<>();
        Map<String, Object> params = new LinkedHashMap<>();
        Arrays.stream(path.split("\\.")).forEach(part -> ids.add(Long.valueOf(part)));

        List<GarHouse> houses = garHouseRepository.findAllByObjectIdIn(ids);
        List<GarAddr> addrs = garAddrRepository.findAllByObjectIdIn(ids);

        addrs.forEach(addr -> {
            params.putAll(addrTypesManager.getObjectParams(addr));
        });

        houses.forEach(house -> {
            params.putAll(houseParamsService.getHouseParams(house));
        });

        String fullAddress = addressNamesService.buildFullAddressFromObjects(addrs);
        String shortAddress = addressNamesService.buildShortAddressFromObjects(addrs);

        if (houses.size() > 0) {
            GarHouse house = houses.get(houses.size() - 1);
            try {
                fullAddress += ", " + houseNamesService.getHouseFullName(house);

                shortAddress += ", " + houseNamesService.getHouseShortName(house);
            } catch (HouseTypeDoesNotExistsException e) {}
        }

        params.put("full_address", fullAddress);
        params.put("short_address", shortAddress);
        params.put("path", path);

        return params;
    }
}
