package dev.curly.pp_3_1_5.api;

import dev.curly.pp_3_1_5.dto.UserDTO;
import dev.curly.pp_3_1_5.exceptions.UserNotFoundException;
import dev.curly.pp_3_1_5.model.User;
import dev.curly.pp_3_1_5.service.UserService;
import dev.curly.pp_3_1_5.utils.mapper.UserMapper;
import org.springframework.http.ResponseEntity;
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
    ResponseEntity<List<UserDTO>> allUsers() {
        var result = userService
                .getAll()
                .stream()
                .map(UserMapper::toDTO)
                .toList();

        return ResponseEntity.ok(result);
    }

    @PostMapping
    ResponseEntity<UserDTO> addUser(@RequestBody User newUser) {
        var user = userService.add(newUser);
        var result = UserMapper.toDTO(user);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    ResponseEntity<UserDTO> oneUser(@PathVariable Long id) {
        var user = userService
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        var result = UserMapper.toDTO(user);

        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    ResponseEntity<UserDTO> updateUser(@RequestBody User newUser, @PathVariable Long id) {
        var user = userService.update(newUser, id);
        var result = UserMapper.toDTO(user);

        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
