package com.titouanaclr.gameshelf.service;

import com.titouanaclr.gameshelf.repository.ExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExchangeService {

    @Autowired
    private ExchangeRepository exchangeRepository;
}
