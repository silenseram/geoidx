package com.ewmw.addr.services.gar.hierarchy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class AddressHouseEndpoint {
    @Getter @Setter
    private String typeName;

    @Getter @Setter
    private String value;
}
