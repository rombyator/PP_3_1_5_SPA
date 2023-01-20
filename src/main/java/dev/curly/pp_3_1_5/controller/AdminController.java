package dev.curly.pp_3_1_5.controller;

import dev.curly.pp_3_1_5.model.User;
import dev.curly.pp_3_1_5.service.RoleService;
import dev.curly.pp_3_1_5.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String dashboard(Model model, @AuthenticationPrincipal User iam) {
        model.addAttribute("users", userService.getAll());
        model.addAttribute("roles", roleService.getAll());
        model.addAttribute("iam", iam);

        return "admin";
    }
}
