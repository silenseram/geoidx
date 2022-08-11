package com.ewmw.addr.services.gar.types;

import com.ewmw.addr.services.gar.hierarchy.HouseTypeDoesNotExistsException;
import com.ewmw.addr.services.gar.xml.XmlHelper;
import com.ewmw.addr.services.gar.xml.XmlNode;
import lombok.AllArgsConstructor;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class HouseTypesService {
    private List<HouseType> houseTypes;

    public int getSize() {
        return this.houseTypes.size();
    }

    public static List<HouseType> getHouseTypesFromXml(String path) throws ParserConfigurationException, IOException, SAXException {
        NodeList objectTypes = XmlHelper.getNodesByName(new File(path), "HOUSETYPE");
        List<HouseType> houseTypes = new ArrayList<>();

        for (int i = 0; i < objectTypes.getLength(); i++) {
            XmlNode node = new XmlNode(objectTypes.item(i));

            String name = node.extractAttribute("NAME");
            String shortName = node.extractAttribute("SHORTNAME");
            String description = node.extractAttribute("DESC");
            String id = node.extractAttribute("ID");
            String isActive = node.extractAttribute("ISACTIVE");

            if (isActive.equals("false"))
                continue;

            HouseType houseType = new HouseType(id, name.toLowerCase(), shortName.toLowerCase(), description);

            houseTypes.add(houseType);
        }

        return houseTypes;
    }

    public HouseType getHouseType(String id) {
        return this.houseTypes
                .stream()
                .filter(houseType -> houseType.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
