package com.ewmw.addr.services.gar.names;

import com.ewmw.addr.models.gar.GarAddr;
import com.ewmw.addr.services.gar.interfaces.LevelNotFoundException;
import com.ewmw.addr.services.gar.Morpher;
import com.ewmw.addr.services.gar.asobj.ObjectTypesService;
import com.ewmw.addr.services.gar.types.HouseTypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AddressNamesService {
    @Autowired
    HouseTypesService houseTypesService;

    @Autowired
    ObjectTypesService levelsManager;

    public String buildFullAddressFromObjects(List<GarAddr> addrs) {
        List<String> parts = new ArrayList<>();

        addrs.forEach(
                addr -> {
                    try {
                        String addrPartName = levelsManager.getLevelFullName(Integer.toString(addr.getLevel()), addr.getTypeName());

                        String fullName = Morpher.getFullName(addrPartName, addr.getName());

                        parts.add(prepareFullName(fullName));

                    } catch (LevelNotFoundException | IOException e) {
                        e.printStackTrace();
                    }
                }
        );

        return String.join(", ", parts);
    }

    public String buildShortAddressFromObjects(List<GarAddr> addrs) {
        List<String> parts = new ArrayList<>();

        addrs.forEach(
                addr -> {
                    parts.add(prepareFullName(addr.getTypeName() + " " + addr.getName()));

                }
        );

        return String.join(", ", parts);
    }

    private String prepareFullName(String name) {
        String fullName = name;

//        if (fullName.indexOf("/") != fullName.lastIndexOf("/")){
//            fullName = fullName.replaceFirst("/", "(");
//            fullName = fullName.replaceFirst("/", ")");
//        }
//
//        if (name.endsWith(" -"))
//            fullName = name.substring(0, name.length() - 2);

        return fullName;
    }
}
