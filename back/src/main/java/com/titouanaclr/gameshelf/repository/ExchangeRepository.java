package com.titouanaclr.gameshelf.repository;

import com.titouanaclr.gameshelf.model.Exchange;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRepository extends CrudRepository<Exchange, Integer> {
}
