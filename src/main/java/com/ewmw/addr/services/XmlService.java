package com.ewmw.addr.services;


import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Locale;

public class XmlService {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);

//        String name = "Республика Саха /Якутия/";
//
//        if (name.indexOf("/") != name.lastIndexOf("/")){
//            System.out.println("NOT EQUAL");
//        }

    }

    public static void parse() {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            DocumentBuilder documentBuilder = dbf.newDocumentBuilder();

            Document document = documentBuilder.parse(new File("20220324_ED807_full.xml"));

            NodeList bicDirectories = document.getElementsByTagName("BICDirectoryEntry");

            for (int i = 0; i < bicDirectories.getLength(); i++) {
                Node directory = bicDirectories.item(i);

                String bik = directory.getAttributes().getNamedItem("BIC").getNodeValue();

                Node participantInfo = directory.getChildNodes().item(0);

                String name = participantInfo.getAttributes().getNamedItem("NameP").getNodeValue();

                System.out.println(name + " = " + bik);

                //<ParticipantInfo NameP="КУ &quot;МИЛЛЕНИУМ БАНК&quot; (ЗАО) - ГК &quot;АСВ&quot;" RegN="3423" CntrCd="RU" Rgn="45" Ind="109240" Tnp="г" Nnp="Москва" Adr="ул Высоцкого, 4" DateIn="2016-05-17" PtType="90" Srvcs="1" XchType="0" UID="4525597000" ParticipantStatus="PSAC"><RstrList Rstr="URRS" RstrDate="2016-05-17"/></ParticipantInfo>
            }


        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }

    }
}
