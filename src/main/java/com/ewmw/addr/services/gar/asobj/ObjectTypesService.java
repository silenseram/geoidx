package com.ewmw.addr.services.gar.asobj;

import com.ewmw.addr.services.gar.interfaces.HasLevels;
import com.ewmw.addr.services.gar.interfaces.LevelNotFoundException;
import com.ewmw.addr.services.gar.types.ObjectType;
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
import java.util.Optional;

@AllArgsConstructor
public class ObjectTypesService implements HasLevels {
    private List<ObjectType> objectLevels;
    private List<ObjectType> expiredObjectLevels;

    public static ObjectTypesService fromXml(String path) throws ParserConfigurationException, IOException, SAXException {
        NodeList objectTypes = XmlHelper.getNodesByName(new File(path), "ADDRESSOBJECTTYPE");
        List<ObjectType> objectLevels = new ArrayList<>();
        List<ObjectType> expiredObjectLevels = new ArrayList<>();

        for (int i = 0; i < objectTypes.getLength(); i++) {
            XmlNode node = new XmlNode(objectTypes.item(i));

            String level = node.extractAttribute("LEVEL");
            String shortName = node.extractAttribute("SHORTNAME");
            String fullName = node.extractAttribute("NAME");

            ObjectType objectType = new ObjectType(level, shortName, fullName);

            if (node.isExpired())
                expiredObjectLevels.add(objectType);
            else
                objectLevels.add(objectType);
        }

        return new ObjectTypesService(objectLevels, expiredObjectLevels);
    }

    @Override
    public String getLevelFullName(String level, String shortName) throws LevelNotFoundException {
        return this.getOrThrow(level, shortName).getFullName().toLowerCase();
    }

    private ObjectType getOrThrow(String level, String shortName) throws LevelNotFoundException {
        Optional<ObjectType> objectLevel = objectLevels.stream().filter(
                objectType ->
                        objectType.getLevel().equals(level) &&
                        objectType.getShortName().equals(shortName)
        ).findAny();

        if (objectLevel.isPresent())
            return objectLevel.get();

        Optional<ObjectType> expiredObjectLevel = expiredObjectLevels.stream().filter(
                objectType ->
                        objectType.getLevel().equals(level) &&
                                objectType.getShortName().equals(shortName)
        ).findAny();

        if (! expiredObjectLevel.isPresent())
            throw new LevelNotFoundException("Level " + level + " not found with " + shortName + " shortname");

        return expiredObjectLevel.get();

    }
}
