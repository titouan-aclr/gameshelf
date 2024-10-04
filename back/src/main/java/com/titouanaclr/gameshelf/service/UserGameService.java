package com.titouanaclr.gameshelf.service;

import com.titouanaclr.gameshelf.repository.UserGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserGameService {

    @Autowired
    private UserGameRepository userGameRepository;
}
