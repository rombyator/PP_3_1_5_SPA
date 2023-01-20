package dev.curly.pp_3_1_5.api;

import dev.curly.pp_3_1_5.dto.UserDto;
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
    List<UserDto> allUsers() {
        return userService
                .getAll()
                .stream()
                .map(User::toDto)
                .toList();
    }

    @PostMapping
    UserDto addUser(@RequestBody User newUser) {
        var user = userService.add(newUser);

        return User.toDto(user);
    }

    @GetMapping("/{id}")
    UserDto oneUser(@PathVariable Long id) {
        var user = userService
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        return User.toDto(user);
    }

    @PutMapping("/{id}")
    UserDto updateUser(@RequestBody User newUser, @PathVariable Long id) {
        var user = userService.update(newUser, id);

        return User.toDto(user);
    }

    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable Long id) {
        userService.delete(id);
    }
}
