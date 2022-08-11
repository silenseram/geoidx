package com.ewmw.addr.services.gar.asobj;

import com.ewmw.addr.interfaces.Processor;
import com.ewmw.addr.models.gar.GarAddr;
import com.ewmw.addr.models.reposotories.GarAddrRepository;
import com.ewmw.addr.services.gar.files.GarFilesFilter;
import com.ewmw.addr.services.gar.files.GarFilesManager;
import com.ewmw.addr.services.gar.xml.XmlStreamNode;
import com.ewmw.addr.services.gar.xml.parser.XmlFilesProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import java.io.FileNotFoundException;
import java.util.ArrayList;

@Service
public class GarProcessor extends XmlFilesProcessor implements Processor {

    @Autowired
    GarFilesFilter filesFilter;

    @Autowired
    GarFilesManager filesManager;

    @Autowired
    GarAddrRepository addrRepository;

    @Override
    public void process() {
        filesManager.getAsObjAddressesFiles()
                .forEach(file -> {
                    executor.submit(() -> {
                        try {
                            parseXml(file.getAbsolutePath(), "OBJECT", this::handleXml);
                        } catch (FileNotFoundException | XMLStreamException e) {
                            e.printStackTrace();
                        }
                    });
                });

        waitForFutures();
    }

    private Void handleXml(StartElement node) {
        XmlStreamNode xmlNode = new XmlStreamNode(node);
        if (! xmlNode.isActualAndActive())
            return null;

        String id = xmlNode.extractAttribute("ID");
        String objectId = xmlNode.extractAttribute("OBJECTID");
        String objectGuid = xmlNode.extractAttribute("OBJECTGUID");
        String name = xmlNode.extractAttribute("NAME");
        String typeName = xmlNode.extractAttribute("TYPENAME");
        String level = xmlNode.extractAttribute("LEVEL");

        String PREVID = xmlNode.extractAttribute("PREVID");
        String NEXTID = xmlNode.extractAttribute("NEXTID");
        String CHANGEID = xmlNode.extractAttribute("CHANGEID");
        String OPERTYPEID = xmlNode.extractAttribute("OPERTYPEID");
        String STARTDATE = xmlNode.extractAttribute("STARTDATE");
        String ENDDATE = xmlNode.extractAttribute("ENDDATE");
        String UPDATEDATE = xmlNode.extractAttribute("UPDATEDATE");

        GarAddr garAddr = new GarAddr();

        garAddr.setId((long) Integer.parseInt(id));
        garAddr.setObjectId(Long.valueOf(objectId));
        garAddr.setObjectGuid(objectGuid);
        garAddr.setName(name);
        garAddr.setTypeName(typeName);
        garAddr.setLevel(Integer.parseInt(level));
        garAddr.setPrevId(PREVID);
        garAddr.setNextId(NEXTID);
        garAddr.setChangeId(CHANGEID);
        garAddr.setOperTypeId(OPERTYPEID);
        garAddr.setStartDate(STARTDATE);
        garAddr.setEndDate(ENDDATE);
        garAddr.setUpdateDate(UPDATEDATE);

        addrRepository.save(garAddr);

        return null;
    }

    public boolean breakpoint(String objid) {
        ArrayList<Object> strs = new ArrayList<>();
        strs.add("85486215");
        strs.add("86574715");
        strs.add("88072052");
        strs.add("88446634");
        strs.add("88457732");
        strs.add("88748103");
        strs.add("91348320");
        strs.add("93733656");

        return strs.stream().anyMatch(objid::equals);
    }
}
