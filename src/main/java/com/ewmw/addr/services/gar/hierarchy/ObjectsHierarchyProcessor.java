package com.ewmw.addr.services.gar.hierarchy;

import com.ewmw.addr.interfaces.Processor;
import com.ewmw.addr.models.gar.Address;
import com.ewmw.addr.models.gar.GarAddr;
import com.ewmw.addr.models.reposotories.AddressRepository;
import com.ewmw.addr.models.reposotories.GarAddrRepository;
import com.ewmw.addr.models.reposotories.GarHouseRepository;
import com.ewmw.addr.services.gar.interfaces.LevelNotFoundException;
import com.ewmw.addr.services.gar.names.AddressNamesService;
import com.ewmw.addr.services.gar.names.HouseNamesService;
import com.ewmw.addr.services.gar.types.HouseAddtypesService;
import com.ewmw.addr.services.gar.xml.XmlStreamNode;
import com.ewmw.addr.models.gar.GarHouse;
import com.ewmw.addr.services.gar.xml.parser.XmlFilesProcessor;
import com.ewmw.addr.services.gar.files.GarFilesFilter;
import com.ewmw.addr.services.gar.files.GarFilesManager;
import com.ewmw.addr.services.gar.types.HouseTypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

@Service
public class ObjectsHierarchyProcessor extends XmlFilesProcessor implements Processor {
    @Autowired
    GarFilesFilter filesFilter;

    @Autowired
    GarFilesManager filesManager;

    @Autowired
    GarAddrRepository garAddrRepository;

    @Autowired
    GarHouseRepository garHouseRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    HouseNamesService houseNamesService;

    @Autowired
    AddressNamesService addressNamesService;

    @Autowired
    HouseTypesService houseTypesService;

    @Autowired
    HouseAddtypesService houseAddtypesService;

    private int HOUSE_LEVEL = 15;

    @Override
    public void process() {
        processFiles(filesManager.getAsAdmHierarchyFiles());
//        processFiles(filesManager.getAsMunHierarchyFiles());
    }

    private void processFiles(List<File> fileList) {
        fileList
                .forEach(file -> executor.submit(() -> {
                    try {
                        parseXml(file.getAbsolutePath(), "ITEM", this::handleXml);
                        System.out.println("Completed #" + executor.getCompletedTaskCount());
                    } catch (FileNotFoundException | XMLStreamException e) {
                        e.printStackTrace();
                    }
                }));
    }

    private Void handleXml(StartElement startElement) {
        XmlStreamNode node = new XmlStreamNode(startElement);

        if (! node.isActive())
            return null;

        String path = node.extractAttribute("PATH");

        handlePath(path);

        return null;
    }

    private void handlePath(String path) {

        List<String> parts = Arrays.asList(path.split("\\."));

        if (parts.size() < 1)
            return;

        Long lastPart = Long.parseLong(parts.get(parts.size() - 1));

        List<GarHouse> houses = garHouseRepository.findByObjectId(lastPart);

        if (houses.size() < 1) {
            try {
                handleAddressPath(path);
            } catch (LevelNotFoundException | Exception e) {
            }

            return;
        }

        GarHouse house = houses.get(houses.size() - 1);

        try {
            handleHousePath(path, house);
        } catch (HouseTypeDoesNotExistsException | Exception e) {
        }
    }

    private void handleHousePath(String path, GarHouse house) throws HouseTypeDoesNotExistsException, Exception {
        HierarchyPath hierarchyPath = new HierarchyPath(path);
        hierarchyPath.setPath(hierarchyPath.cutLastPart());

        List<GarAddr> garAddrs = getGarAddrs(hierarchyPath.getPath());

        if (house.getHouseNum() == null)
            System.out.println("breakpoint");

        String geoAddr = addressNamesService.buildFullAddressFromObjects(garAddrs);

        String houseFullName = houseNamesService.getHouseFullName(house);

        Address address = new Address();
        address.setAddr(geoAddr + ", " + houseFullName);
        address.setObjectId(house.getObjectId());
        address.setUuid(UUID.fromString(house.getObjectGuid()));
        address.setPath(path);
        address.setLevel(HOUSE_LEVEL);

//        if (house.getAddType1() != null || house.getAddType2() != null)
//            System.out.println(address.getAddr());


        addressRepository.save(address);
    }

    private void handleAddressPath(String path) throws LevelNotFoundException, Exception {
        List<GarAddr> addrs = getGarAddrs(path);
        String fullAddress = addressNamesService.buildFullAddressFromObjects(addrs);

        GarAddr garAddr = addrs.get(addrs.size() - 1);

        Address address = new Address();
        address.setAddr(fullAddress);
        address.setUuid(UUID.fromString(garAddr.getObjectGuid()));
        address.setObjectId(garAddr.getObjectId());
        address.setPath(path);
        address.setLevel(garAddr.getLevel());

        addressRepository.save(address);
    }

    private List<GarAddr> getGarAddrs(String path) throws Exception {
        List<Long> ids = new ArrayList<>();

        Arrays.stream(path.split("\\.")).forEach(e -> ids.add(Long.parseLong(e)));

        List<GarAddr> addrs = garAddrRepository.findAllByObjectIdIn(ids);

        if (addrs.size() != ids.size())
            throw new Exception("Bad addr path passed: " + path + ". Path parts amount does not equals founded objects");

        addrs.sort(Comparator.comparingInt(GarAddr::getLevel));

        return addrs;
    }
}
