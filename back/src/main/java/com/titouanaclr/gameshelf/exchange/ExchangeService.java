package com.titouanaclr.gameshelf.exchange;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExchangeService {

    @Autowired
    private ExchangeRepository exchangeRepository;
}
