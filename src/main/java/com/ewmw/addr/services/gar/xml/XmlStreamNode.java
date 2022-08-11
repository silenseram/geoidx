package com.ewmw.addr.services.gar.xml;

import lombok.AllArgsConstructor;

import javax.xml.namespace.QName;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;

@AllArgsConstructor
public class XmlStreamNode {
    StartElement node;

    public String extractAttribute(String key) {
        Attribute attributeByName = node.getAttributeByName(new QName(key));

        if (attributeByName == null)
            return null;

        return attributeByName.getValue();
    }

    public boolean isActualAndActive() {
        String actual = extractAttribute("ISACTUAL");
        String active = extractAttribute("ISACTIVE");

        return active.equals("1") && actual.equals("1");
    }

    public boolean isActive() {
        String active = extractAttribute("ISACTIVE");

        return active.equals("1");
    }
}
