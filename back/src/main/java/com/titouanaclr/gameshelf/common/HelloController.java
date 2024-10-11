package com.titouanaclr.gameshelf.common;

import com.titouanaclr.gameshelf.security.UserPrincipal;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Hidden
@RequiredArgsConstructor
@RestController
public class HelloController {

    @GetMapping("/")
    public String greeting() {
        return "Hello, World!";
    }

    @GetMapping("/secured")
    public String secured(@AuthenticationPrincipal UserPrincipal principal) {
        return "secured because logged in as user " + principal.getEmail()
                + "\nUser ID : " + principal.getUserId();
    }

    @GetMapping("/admin")
    public String admin(@AuthenticationPrincipal UserPrincipal principal) {
        return "If you see this, you are an admin. User id : " + principal.getUserId();
    }
}
