package dev.curly.pp_3_1_5.controller;

import dev.curly.pp_3_1_5.exceptions.UserEmailAlreadyInUse;
import dev.curly.pp_3_1_5.model.User;
import dev.curly.pp_3_1_5.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ApiControllerV1 {
    private final UserService userService;

    public ApiControllerV1(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute("user") User user) {
        try {
            userService.add(user);
        } catch (UserEmailAlreadyInUse e) {
            return "";
        }

        return "";
    }

    @PutMapping("/{id}/update")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.update(user);

        return "";
    }

    @DeleteMapping("/{id}/delete")
    public String deleteUser(@PathVariable("id") int id) {
        userService.delete(id);

        return "";
    }
}
