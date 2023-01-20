package dev.curly.pp_3_1_5.api;

import dev.curly.pp_3_1_5.dto.UserDto;
import dev.curly.pp_3_1_5.model.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/iam")
public class MyInfoRestController {
    @GetMapping
    UserDto whoAmI(@AuthenticationPrincipal User user) {
        return User.toDto(user);
    }
}
