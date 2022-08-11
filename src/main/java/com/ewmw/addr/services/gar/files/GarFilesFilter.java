package com.ewmw.addr.services.gar.files;

import org.springframework.stereotype.Service;

@Service
public class GarFilesFilter {
    public boolean isAddrObj(String fileName) {
        return
                fileName.startsWith(GarFileType.AS_ADDR_OBJ) &&
                ! fileName.startsWith(GarFileType.AS_ADDR_OBJ_PARAMS) &&
                ! fileName.startsWith(GarFileType.AS_ADDR_OBJ_DIVISION);
    }

    public boolean isHouse(String fileName) {
        return
                fileName.startsWith(GarFileType.AS_HOUSES) &&
                ! fileName.startsWith(GarFileType.AS_HOUSES_PARAMS);
    }

    public boolean isAdmHierarchy(String fileName) {
        return fileName.startsWith(GarFileType.AS_ADM_HIERARCHY);
    }

    public boolean isHouseParams(String fileName) {
        return fileName.startsWith(GarFileType.AS_HOUSES_PARAMS);
    }

    public boolean isMunHierarchy(String fileName) {
        return fileName.startsWith(GarFileType.AS_MUN_HIERARCHY);
    }
}
