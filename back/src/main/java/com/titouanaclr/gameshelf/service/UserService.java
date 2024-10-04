package com.titouanaclr.gameshelf.service;

import com.titouanaclr.gameshelf.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
}
