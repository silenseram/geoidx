package com.ewmw.addr.services.gar.ashouse;

import com.ewmw.addr.interfaces.Processor;
import com.ewmw.addr.models.gar.GarHouse;
import com.ewmw.addr.models.reposotories.GarHouseRepository;
import com.ewmw.addr.services.gar.files.GarFilesManager;
import com.ewmw.addr.services.gar.xml.XmlStreamNode;
import com.ewmw.addr.services.gar.xml.parser.XmlFilesProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import java.io.FileNotFoundException;

@Service
public class AsHouseProcessor extends XmlFilesProcessor implements Processor {

    @Autowired
    GarFilesManager filesManager;

    @Autowired
    GarHouseRepository houseRepository;

    @Autowired
    protected ApplicationContext springContext;

    @Override
    public void process() {

//        try {
//            parseXml("C:\\openserv\\OSPanel\\nework\\jjjjjjjj\\gs-spring-boot\\initial\\gar_ext\\34\\AS_HOUSES_20220324_64ed10ab-fdbe-4865-99e2-460f2adf7c3a.XML",
//                    "HOUSE", this::processHouse);
//        } catch (FileNotFoundException | XMLStreamException e) {
//            e.printStackTrace();
//        }
        filesManager.getAsHouseFiles()
                .forEach((file) ->
                        executor.submit(() -> {
                            try {
                                parseXml(file.getAbsolutePath(), "HOUSE", this::processHouse);
                            } catch (FileNotFoundException | XMLStreamException e) {
                                e.printStackTrace();
                            }
                        })
                );

        waitForFutures();
    }

    private Void processHouse(StartElement node) {
        XmlStreamNode xmlNode = new XmlStreamNode(node);

        String id = xmlNode.extractAttribute("ID");
        String objectId = xmlNode.extractAttribute("OBJECTID");
        String objectGuid = xmlNode.extractAttribute("OBJECTGUID");
        String houseType = xmlNode.extractAttribute("HOUSETYPE");
        String houseNum = xmlNode.extractAttribute("HOUSENUM");
        String ISACTUAL = xmlNode.extractAttribute("ISACTUAL");
        String ISACTIVE = xmlNode.extractAttribute("ISACTIVE");

        String ADDNUM1 = xmlNode.extractAttribute("ADDNUM1");
        String ADDNUM2 = xmlNode.extractAttribute("ADDNUM2");

        String ADDTYPE1 = xmlNode.extractAttribute("ADDTYPE1");
        String ADDTYPE2 = xmlNode.extractAttribute("ADDTYPE2");

        try {
            GarHouse garHouse = new GarHouse();

            garHouse.setId(Long.parseLong(id));
            garHouse.setHouseNum(houseNum);
            garHouse.setObjectId(Long.parseLong(objectId));
            garHouse.setObjectGuid(objectGuid);
            garHouse.setHouseType(longOrNull(houseType));
            garHouse.setIsActive(Integer.parseInt(ISACTIVE));
            garHouse.setIsActual(Integer.parseInt(ISACTUAL));

            garHouse.setAddNum1(ADDNUM1);
            garHouse.setAddNum2(ADDNUM2);

            garHouse.setAddType1(ADDTYPE1);
            garHouse.setAddType2(ADDTYPE2);

//            houseRepository.save(garHouse);
        } catch (Exception e) {
            System.out.println("SUKA dom ne soxranen");
            e.printStackTrace();
        }

        return null;
    }

    private Long longOrNull(String num) {
        try {
            return Long.parseLong(num);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    //    private Void processXml(File file) {
//        try {
//            NodeList nodes = XmlHelper.getNodesByName(file, "HOUSE");
//
//            for (int i = 0; i < nodes.getLength(); i++) {
//                Node item = nodes.item(i);
//
//                System.out.println("Processing" + item.getAttributes().getNamedItem("ID").getNodeValue() + " :-: " + i);
//
//                processHouse(item);
//            }
//
//            System.out.println(file.getAbsolutePath() + " is done");
//        } catch (ParserConfigurationException | IOException | SAXException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("we must return...");
//        ret
//    }
}
