package com.ewmw.addr.config.services;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter @Setter
@Service
public class GarConfig {
    private String typesDirectory = "gar_ext\\types";
    private String asHouseTypesPrefix = "AS_HOUSE_TYPES_";
    private String asAddhouseTypesPrefix = "AS_ADDHOUSE_TYPES_";
    private String asAddrObjTypesPrefix = "AS_ADDR_OBJ_TYPES_";
}
