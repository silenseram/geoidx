package com.ewmw.addr.services.gar.asparams;

import com.ewmw.addr.models.gar.GarHouse;
import com.ewmw.addr.models.reposotories.GarHouseRepository;
import com.ewmw.addr.services.gar.params.HouseParamsTypesService;
import com.ewmw.addr.services.gar.xml.XmlStreamNode;
import com.ewmw.addr.services.gar.hierarchy.HouseDoesNotExistsException;
import com.ewmw.addr.services.gar.interfaces.XmlProcessor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.stream.events.StartElement;
import java.util.List;

@Component
@NoArgsConstructor
public class ParamsXmlProcessor implements XmlProcessor {
    GarHouse currentHouse;

    @Getter @Setter
    @Autowired
    private GarHouseRepository garHouseRepository;

    @Getter @Setter
    @Autowired
    private HouseParamsTypesService houseParamsTypesService;

    public ParamsXmlProcessor(GarHouseRepository garHouseRepository, HouseParamsTypesService houseParamsTypesService) {
        this.garHouseRepository = garHouseRepository;
        this.houseParamsTypesService = houseParamsTypesService;
    }

    @Override
    public void processNode(StartElement startElement) throws HouseDoesNotExistsException {
        XmlStreamNode node = new XmlStreamNode(startElement);

        Long objectId = Long.valueOf(node.extractAttribute("OBJECTID"));

        if (! isSameHouseId(objectId)) {
            List<GarHouse> byObjectId = garHouseRepository.findByObjectId(objectId);

            if (byObjectId.size() < 1)
                throw new HouseDoesNotExistsException("House");

            saveCurrent();
            currentHouse = byObjectId.get(0);
        }

        String typeId = node.extractAttribute("TYPEID");
        String value = node.extractAttribute("VALUE");

        currentHouse = houseParamsTypesService.setHouseParam(typeId, value, currentHouse);
//        System.out.println("+");
    }

    private void saveCurrent() {
        if (currentHouse == null)
            return;

        garHouseRepository.save(currentHouse);
    }

    private boolean isSameHouseId(Long id) {
        if (currentHouse == null)
            return false;

        return currentHouse.getObjectId().equals(id);
    }

    @Override
    public void finish() {
        System.out.println("#" + Thread.currentThread().getId() + " finished");
    }
}
