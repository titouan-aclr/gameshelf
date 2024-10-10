package com.titouanaclr.gameshelf.repository;

import com.titouanaclr.gameshelf.model.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends CrudRepository<Location, Integer> {

    public Iterable<Location> findByUserId(int userId);
}
