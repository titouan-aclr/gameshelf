package com.titouanaclr.gameshelf.service;

import com.titouanaclr.gameshelf.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;
}
