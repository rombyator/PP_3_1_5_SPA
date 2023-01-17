package dev.curly.pp_3_1_5.controller;

import dev.curly.pp_3_1_5.exceptions.UserNotFoundException;
import dev.curly.pp_3_1_5.model.User;
import dev.curly.pp_3_1_5.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UsersRestController {
    private final UserService userService;

    public UsersRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    List<User> allUsers() {
        return userService.getAll();
    }

    @PostMapping
    User addUser(@RequestBody User newUser) {
        return userService.add(newUser);
    }

    @GetMapping("/{id}")
    User oneUser(@PathVariable Long id) {
        return userService.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @PutMapping("/{id}")
    User updateUser(@RequestBody User user, @PathVariable Long id) {
        return userService.update(user, id);
    }

    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable Long id) {
        userService.delete(id);
    }
}
