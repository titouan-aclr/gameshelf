package com.titouanaclr.gameshelf.location;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends CrudRepository<Location, Integer> {

    public Iterable<Location> findByUserId(int userId);
}
