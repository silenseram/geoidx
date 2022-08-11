package com.ewmw.addr.services.address.types;

import com.ewmw.addr.models.gar.GarAddr;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AddrTypesManager {
    public HashMap<String, Object> getObjectParams(GarAddr addr) {
        HashMap<String, Object> objectParams = new HashMap<>();

        int level = addr.getLevel();

        switch (level) {
            case 1:
                objectParams = getLevel1Params(addr);
                break;
            case 2:
                objectParams = getLevel2Params(addr);
                break;
            case 3:
                objectParams = getLevel3Params(addr);
                break;
            case 4:
                objectParams = getLevel4Params(addr);
                break;
            case 5:
                objectParams = getLevel5Params(addr);
                break;
            case 6:
                objectParams = getLevel6Params(addr);
                break;
            case 7:
                objectParams = getLevel7Params(addr);
                break;
            case 8:
                objectParams = getLevel8Params(addr);
                break;
            case 14:
                objectParams = getLevel14Params(addr);
                break;
        }

        return objectParams;
    }

    private HashMap<String, Object> getLevel1Params(GarAddr addr) {
        return fillWithDefaults("region", addr);
    }

    private HashMap<String, Object> getLevel2Params(GarAddr addr) {
        return fillWithDefaults("administrative_area", addr);
    }

    private HashMap<String, Object> getLevel3Params(GarAddr addr) {
        return fillWithDefaults("municipal_area", addr);
    }

    private HashMap<String, Object> getLevel4Params(GarAddr addr) {
        return fillWithDefaults("settlement", addr);
    }

    private HashMap<String, Object> getLevel5Params(GarAddr addr) {
        return fillWithDefaults("city", addr);
    }

    private HashMap<String, Object> getLevel6Params(GarAddr addr) {
        return fillWithDefaults("locality", addr);
    }

    private HashMap<String, Object> getLevel7Params(GarAddr addr) {
        return fillWithDefaults("block", addr);
    }

    private HashMap<String, Object> getLevel8Params(GarAddr addr) {
        return fillWithDefaults("street", addr);
    }

    private HashMap<String, Object> getLevel14Params(GarAddr addr) {
        return fillWithDefaults("city_area", addr);
    }

    private HashMap<String, Object> fillWithDefaults(String prefix, GarAddr addr) {
        HashMap<String, Object> objectParams = new HashMap<>();

        objectParams.put(prefix + "_name", addr.getName());
        objectParams.put(prefix + "_type", addr.getTypeName());
        objectParams.put(prefix + "_guid", addr.getObjectGuid());
        objectParams.put(prefix + "_id", addr.getObjectId());

        return objectParams;
    }
}
