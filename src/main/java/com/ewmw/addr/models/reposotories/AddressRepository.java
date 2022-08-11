package com.ewmw.addr.models.reposotories;

import com.ewmw.addr.models.gar.Address;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

public interface AddressRepository extends CrudRepository<Address, Long> {
    ArrayList<Address> findAllByObjectIdIn(List<Long> ids);

//    @Query("select a from Address a")
//    ArrayList<Address> findWithOffsetAndLimit(Pageable pageable);

    ArrayList<Address> findAllByObjectIdInOrderByLevel(List<Long> ids);
}
