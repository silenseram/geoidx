package com.ewmw.addr.services.gar.names;

import com.ewmw.addr.services.gar.hierarchy.AddressHouseEndpoint;
import com.ewmw.addr.services.gar.hierarchy.HouseTypeDoesNotExistsException;
import com.ewmw.addr.services.gar.types.HouseAddtypesService;
import com.ewmw.addr.models.gar.GarHouse;
import com.ewmw.addr.services.gar.types.HouseType;
import com.ewmw.addr.services.gar.types.HouseTypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HouseNamesService {

    @Autowired
    HouseTypesService houseTypesService;

    @Autowired
    HouseAddtypesService houseAddtypesService;

    public String getHouseFullName(GarHouse garHouse) throws HouseTypeDoesNotExistsException {
        ArrayList<AddressHouseEndpoint> addressHouseEndpoints = new ArrayList<>();

        String houseNum = garHouse.getHouseNum();
        Long houseType = garHouse.getHouseType();

        String addType1 = garHouse.getAddType1();
        String addType2 = garHouse.getAddType2();

        String addNum1 = garHouse.getAddNum1();
        String addNum2 = garHouse.getAddNum2();

        if (houseNum != null) {
            addressHouseEndpoints.add(
                    new AddressHouseEndpoint(houseTypesService.getHouseType(String.valueOf(houseType)).getFullName(), houseNum)
            );
        }

        if (addNum1 != null) {
            addressHouseEndpoints.add(
                    getFullNameAddTypeEndpoint(addNum1, addType1)
            );
        }

        if (addNum2 != null) {
            addressHouseEndpoints.add(
                    getFullNameAddTypeEndpoint(addNum2, addType2)
            );
        }

        String result = getFullName(addressHouseEndpoints);

        return result;
    }

    private AddressHouseEndpoint getFullNameAddTypeEndpoint(String addNum, String addType) {
        HouseType houseType = houseAddtypesService.getHouseType(String.valueOf(addType));

        String houseTypeName = "";

        if (houseType != null)
            houseTypeName = houseType.getFullName();

        return new AddressHouseEndpoint(houseTypeName, addNum);
    }

    private AddressHouseEndpoint getShortNameAddTypeEndpoint(String addNum, String addType) {
        HouseType houseType = houseAddtypesService.getHouseType(String.valueOf(addType));

        String houseTypeName = "";

        if (houseType != null)
            houseTypeName = houseType.getShortName();

        return new AddressHouseEndpoint(houseTypeName, addNum);
    }


    public String getHouseShortName(GarHouse garHouse) throws HouseTypeDoesNotExistsException {
        ArrayList<AddressHouseEndpoint> addressHouseEndpoints = new ArrayList<>();

        String houseNum = garHouse.getHouseNum();
        Long houseType = garHouse.getHouseType();

        String addType1 = garHouse.getAddType1();
        String addType2 = garHouse.getAddType2();

        String addNum1 = garHouse.getAddNum1();
        String addNum2 = garHouse.getAddNum2();

        if (houseNum != null)
            addressHouseEndpoints.add(
                    new AddressHouseEndpoint(houseTypesService.getHouseType(String.valueOf(houseType)).getShortName(), houseNum)
            );

        if (addNum1 != null)
            addressHouseEndpoints.add(
                    getShortNameAddTypeEndpoint(addNum1, addType1)
            );

        if (addNum2 != null)
            addressHouseEndpoints.add(
                    getShortNameAddTypeEndpoint(addNum2, addType2)
            );

        return getFullName(addressHouseEndpoints);
    }

    public String getFullName(ArrayList<AddressHouseEndpoint> addressHouseEndpoints) throws HouseTypeDoesNotExistsException {
        List<String> pathParts = new ArrayList<>();
        StringBuilder fullName = new StringBuilder();
        int endpointsAmount = addressHouseEndpoints.size();


        for (int i = 0; i < endpointsAmount; i++) {
            AddressHouseEndpoint endpoint = addressHouseEndpoints.get(i);

            String typeName = endpoint.getTypeName();
            String value = endpoint.getValue();

            String strEndpoint;

            if (typeName.equals("")) {
                fullName.append(" ").append(value);
                continue;
            }

            strEndpoint = typeName + " " + value;

            if (i != 0)
                strEndpoint = ", " + strEndpoint;

            fullName.append(strEndpoint);
        }

//        for (AddressHouseEndpoint endpoint : addressHouseEndpoints) {
//
//            pathParts.add(endpoint.getTypeName() + " " + endpoint.getValue());
//
//        }
//
//        return String.join(", ", pathParts);


        return fullName.toString();
    }
}
