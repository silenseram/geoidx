package com.ewmw.addr.services.gar.ashouse;

import com.ewmw.addr.models.reposotories.GarHouseRepository;
import com.ewmw.addr.models.gar.GarHouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class HousesBuffer {

    @Autowired
    GarHouseRepository garHouseRepository;

    private static int BUFFER_SIZE = 1000;
    List<GarHouse> garHouses;

    public HousesBuffer() {
        this.garHouses = new ArrayList<>();


    }

    public void add(GarHouse house) {
        garHouses.add(house);

        if (garHouses.size() > BUFFER_SIZE)
            garHouseRepository.saveAll(garHouses);

        garHouses.clear();
    }
}
