package com.ewmw.addr.services.gar.files;

import java.util.Arrays;
import java.util.List;

public final class GarFileType {
    public static final String AS_ADDR_OBJ = "AS_ADDR_OBJ_";
    public static final String AS_ADDR_OBJ_DIVISION = "AS_ADDR_OBJ_DIVISION_";
    public static final String AS_HOUSES = "AS_HOUSES_";
    public static final String AS_ADM_HIERARCHY = "AS_ADM_HIERARCHY_";
    public static final String AS_MUN_HIERARCHY = "AS_MUN_HIERARCHY_";
    public static final String AS_ADDR_OBJ_PARAMS = "AS_ADDR_OBJ_PARAMS_";
    public static final String AS_HOUSES_PARAMS = "AS_HOUSES_PARAMS_";
    public static final String AS_STEADS = "AS_STEADS_";

    public static List<String> PREFIXES =
            Arrays.asList(
//                    GarFileType.AS_ADDR_OBJ, GarFileType.AS_HOUSES, GarFileType.AS_ADM_HIERARCHY,
//                    GarFileType.AS_MUN_HIERARCHY, GarFileType.AS_ADDR_OBJ_PARAMS, GarFileType.AS_HOUSES_PARAMS
                    AS_STEADS
            );

}
