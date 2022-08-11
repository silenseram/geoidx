package com.ewmw.addr.services.gar.types;

import java.util.List;

public class HouseAddtypesService extends HouseTypesService {

    public HouseAddtypesService(List<HouseType> houseTypes) {
        super(houseTypes);
    }

//    public static HouseAddtypesService fromXml(String path) throws ParserConfigurationException, IOException, SAXException {
//        return new HouseAddtypesService(HouseTypesService.getHouseTypesFromXml(path));
//    }
}
