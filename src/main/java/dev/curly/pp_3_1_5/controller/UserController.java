package dev.curly.pp_3_1_5.controller;

import dev.curly.pp_3_1_5.model.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {
    @GetMapping()
    public String show(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("currentUser", user);

        return "user";
    }
}
