package com.ewmw.addr.services.gar.types;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class ObjectType {
    @Getter @Setter
    private String level;

    @Getter @Setter
    private String shortName;

    @Getter @Setter
    private String fullName;
}
