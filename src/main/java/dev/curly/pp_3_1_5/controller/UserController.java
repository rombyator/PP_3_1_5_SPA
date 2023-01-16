package dev.curly.pp_3_1_5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {
    @GetMapping()
    public String dashboard() {
        return "user";
    }
}
