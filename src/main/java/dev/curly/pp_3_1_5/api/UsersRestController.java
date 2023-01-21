package dev.curly.pp_3_1_5.api;

import dev.curly.pp_3_1_5.dto.UserDTO;
import dev.curly.pp_3_1_5.exceptions.UserNotFoundException;
import dev.curly.pp_3_1_5.model.User;
import dev.curly.pp_3_1_5.service.UserService;
import dev.curly.pp_3_1_5.utils.mapper.UserMapper;
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
    List<UserDTO> allUsers() {
        return userService
                .getAll()
                .stream()
                .map(UserMapper::toDTO)
                .toList();
    }

    @PostMapping
    UserDTO addUser(@RequestBody User newUser) {
        var user = userService.add(newUser);

        return UserMapper.toDTO(user);
    }

    @GetMapping("/{id}")
    UserDTO oneUser(@PathVariable Long id) {
        var user = userService
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        return UserMapper.toDTO(user);
    }

    @PutMapping("/{id}")
    UserDTO updateUser(@RequestBody User newUser, @PathVariable Long id) {
        var user = userService.update(newUser, id);

        return UserMapper.toDTO(user);
    }

    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable Long id) {
        userService.delete(id);
    }
}
