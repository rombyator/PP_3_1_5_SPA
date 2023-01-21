package dev.curly.pp_3_1_5.api;

import dev.curly.pp_3_1_5.dto.UserDTO;
import dev.curly.pp_3_1_5.model.User;
import dev.curly.pp_3_1_5.utils.mapper.UserMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/iam")
public class MyInfoRestController {
    @GetMapping
    UserDTO whoAmI(@AuthenticationPrincipal User user) {
        return UserMapper.toDTO(user);
    }
}
