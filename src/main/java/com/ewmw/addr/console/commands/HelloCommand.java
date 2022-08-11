package com.ewmw.addr.console.commands;

import com.ewmw.addr.console.commands.kernel.AbstractConsoleCommand;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@NoArgsConstructor
public class HelloCommand extends AbstractConsoleCommand {
//
//    @Autowired
//    HouseTypesService houseTypesService;

    public HelloCommand(String[] args) {
        super(args);
    }

//    @Autowired
//    private AddressRepository addressRepository;
//
//    @Autowired
//    private GarAddrRepository garAddrRepository;
//
//    @Autowired
//    GarHouseRepository garHouseRepository;
//
//    @Autowired
//    private SphinxMatcher matcher;
//
//    @Autowired
//    AddressNamesService addressNamesService;
//
//    @Autowired
//    HouseNamesService houseNamesService;
//
//    HouseTypesService houseTypesManager;

    private int HOUSE_LEVEL = 15;

    @Override
    public void run() throws Exception {
//        System.out.println(houseTypesService.getSize());
//        houseTypesManager = HouseTypesService.fromXml("gar_ext\\types\\AS_ADDHOUSE_TYPES_20220324_fcb40e02-edd8-4d82-bc8d-61cf99d370e3.XML");
//
//        handlePath("1414662.1450206.81251857");
    }

//    private void handlePath(String path) {
//
//        List<String> parts = Arrays.asList(path.split("\\."));
//
//        if (parts.size() < 1)
//            return;
//
//        Long lastPart = Long.parseLong(parts.get(parts.size() - 1));
//
//        List<GarHouse> houses = garHouseRepository.findByObjectId(lastPart);
//
//        if (houses.size() < 1) {
//            try {
//                handleAddressPath(path);
//            } catch (LevelNotFoundException | Exception e) {
//            }
//
//            return;
//        }
//
//        GarHouse house = houses.get(houses.size() - 1);
//
//        try {
//            handleHousePath(path, house);
//        } catch (HouseTypeDoesNotExistsException | Exception e) {}
//    }
//
//    private void handleHousePath(String path, GarHouse house) throws HouseTypeDoesNotExistsException, Exception {
//        HierarchyPath hierarchyPath = new HierarchyPath(path);
//        hierarchyPath.setPath(hierarchyPath.cutLastPart());
//
//        List<GarAddr> garAddrs = getGarAddrs(hierarchyPath.getPath());
//
//        if (house.getHouseNum() == null)
//            System.out.println("breakpoint");
//
//        String geoAddr = addressNamesService.buildAddressFromObjects(garAddrs);
//        String strHouseNameValue = String.valueOf(house.getHouseNum());
//        HouseType houseType = houseTypesManager.getHouseType(String.valueOf(house.getHouseType()));
//
//        String houseFullName = houseNamesService.getHouseFullName(house);
//
//        Address address = new Address();
////        address.setPrefix(houseType.getFullName());
////        address.setPrefixShort(houseType.getShortName());
//        address.setAddr(geoAddr + ", " + houseFullName);
////        address.setNameValue(strHouseNameValue);
//        address.setObjectId(house.getObjectId());
//        address.setUuid(UUID.fromString(house.getObjectGuid()));
//        address.setPath(path);
//        address.setLevel(HOUSE_LEVEL);
//
//        System.out.println("ini");
//
//
////        addressRepository.save(address);
//    }
//
//    private void handleAddressPath(String path) throws LevelNotFoundException, Exception {
//        List<GarAddr> addrs = getGarAddrs(path);
//        String fullAddress = addressNamesService.buildAddressFromObjects(addrs);
//
//        GarAddr garAddr = addrs.get(addrs.size() - 1);
//
//        Address address = new Address();
//        address.setAddr(fullAddress);
//        address.setUuid(UUID.fromString(garAddr.getObjectGuid()));
//        address.setObjectId(garAddr.getObjectId());
//        address.setPath(path);
//        address.setLevel(garAddr.getLevel());
//
////        addressRepository.save(address);
//    }
//
//    private List<GarAddr> getGarAddrs(String path) throws Exception {
//        List<Long> ids = new ArrayList<>();
//
//        Arrays.stream(path.split("\\.")).forEach(e -> ids.add(Long.parseLong(e)));
//
//        List<GarAddr> addrs = garAddrRepository.findAllByObjectIdIn(ids);
//
//        if (addrs.size() != ids.size())
//            throw new Exception("Bad addr path passed: " + path + ". Path parts amount does not equals founded objects");
//
//        addrs.sort(Comparator.comparingInt(GarAddr::getLevel));
//
//        return addrs;
//    }
}
