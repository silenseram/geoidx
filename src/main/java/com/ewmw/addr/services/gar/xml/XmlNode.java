package com.ewmw.addr.services.gar.xml;

import lombok.AllArgsConstructor;

import javax.xml.namespace.QName;
import org.w3c.dom.Node;
import javax.xml.stream.events.StartElement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
public class XmlNode {
    Node node;

    public String extractAttribute(String key) {
        return node.getAttributes().getNamedItem(key).getNodeValue();
    }

    public boolean isActualAndActive() {
        return isActive("1") && isActual();
    }

    public boolean isActive(String value) {
        String active = extractAttribute("ISACTIVE");

        String val = value == null ? "1" : value;

        return active.equals(val);
    }

    public boolean isExpired() {
        String strDate = extractAttribute("ENDDATE");

        LocalDate date = LocalDate.parse(strDate);

        LocalDate nowDate = LocalDate.now();

        return date.isBefore(nowDate);
    }

    public boolean isActual() {
        String actual = extractAttribute("ISACTUAL");

        return actual.equals("1");
    }
}
