package com.ewmw.addr.services.gar.types;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class HouseType {
    @Getter
    private String id;

    @Getter
    private String fullName;

    @Getter
    private String shortName;

    @Getter
    private String description;
}
