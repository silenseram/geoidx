package com.ewmw.addr.services.gar;

import com.github.demidko.aot.PartOfSpeech;
import com.github.demidko.aot.WordformMeaning;

import java.io.IOException;
import java.util.List;

import static com.github.demidko.aot.WordformMeaning.lookupForMeanings;

public class Morpher {

    public static String getFullName(String typeName, String nameValue) throws IOException {
        if (isTypeMustBeFirst(typeName))
            return typeName + " " + nameValue;

        String[] nameValueParts = nameValue.split(" ");
        String lastNameValue = nameValueParts[nameValueParts.length - 1];

        List<WordformMeaning> meanings = lookupForMeanings(lastNameValue);

        if (meanings.size() < 1)
            return typeName + " " + nameValue;

        WordformMeaning meaning = meanings.get(0);

        PartOfSpeech partOfSpeech = meaning.getPartOfSpeech();

        if (partOfSpeech.equals(PartOfSpeech.Noun))
            return typeName + " " + nameValue;

        return nameValue + " " + typeName;
    }

    protected static String[] firstOrderTypes = new String[]{"остров", "улица", "село", "город"};

    protected static boolean isTypeMustBeFirst(String typeName) {
        for (String type : firstOrderTypes) {
            if (type.equalsIgnoreCase(typeName))
                return true;
        }

        return false;
    }
}
