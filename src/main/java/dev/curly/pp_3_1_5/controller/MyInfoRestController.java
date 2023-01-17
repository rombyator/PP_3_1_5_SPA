package dev.curly.pp_3_1_5.controller;

import dev.curly.pp_3_1_5.model.User;
import dev.curly.pp_3_1_5.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/iam")
public class MyInfoRestController {
    private final UserService userService;

    public MyInfoRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    User whoAmI(@AuthenticationPrincipal User user) {
        return user;
    }
}
