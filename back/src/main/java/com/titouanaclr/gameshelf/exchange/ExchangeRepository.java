package com.titouanaclr.gameshelf.exchange;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRepository extends CrudRepository<Exchange, Integer> {
}
