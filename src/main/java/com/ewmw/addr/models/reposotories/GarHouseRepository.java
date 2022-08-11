package com.ewmw.addr.models.reposotories;

import com.ewmw.addr.models.gar.GarHouse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GarHouseRepository extends CrudRepository<GarHouse, Long> {
    @Query("SELECT g FROM GarHouse g WHERE g.objectId=:objectId AND g.isActive='1' AND g.isActual='1'")
    List<GarHouse>  findByObjectId(Long objectId);

//    @Query("SELECT g FROM GarHouse g WHERE g.objectId IN (:objectIds)")
    List<GarHouse> findAllByObjectIdIn(List<Long> objectIds);

    @Query("DELETE FROM GarHouse g WHERE g.objectId=:objectId")
    void deleteByObjectId(Long objectId);

    @Query("select g from GarHouse g")
    List<GarHouse> findWithOffsetAndLimit(Pageable pageable);
}
