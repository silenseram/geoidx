package com.ewmw.addr.services.database;

import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OffsetQueryable<T> {
    List<T> getWithOffsetAndLimit(long offset, long limit);
}
