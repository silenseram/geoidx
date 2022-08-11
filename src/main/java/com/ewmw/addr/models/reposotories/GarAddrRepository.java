package com.ewmw.addr.models.reposotories;

import com.ewmw.addr.models.gar.GarAddr;
import com.ewmw.addr.models.gar.GarHouse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface GarAddrRepository extends CrudRepository<GarAddr, Long> {
    public List<GarAddr> findAllByObjectIdIn(List<Long> objectIds);

    @Query("select g from GarAddr g")
    List<GarAddr> findWithOffsetAndLimit(Pageable pageable);
}
