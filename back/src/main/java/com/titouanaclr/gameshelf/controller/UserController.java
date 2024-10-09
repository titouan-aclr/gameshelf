package com.titouanaclr.gameshelf.controller;

import com.titouanaclr.gameshelf.model.UserProfileResponse;
import com.titouanaclr.gameshelf.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("{user-id}")
    public ResponseEntity<UserProfileResponse> findUserProfileById(@PathVariable("user-id") Integer userId) {
        return ResponseEntity.ok(this.userService.findUserProfileById(userId));
    }

}
