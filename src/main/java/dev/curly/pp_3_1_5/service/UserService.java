package dev.curly.pp_3_1_5.service;

import dev.curly.pp_3_1_5.model.Role;
import dev.curly.pp_3_1_5.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAll();

    Optional<User> findById(Long id);

    User add(User newUser);

    User update(User newUser, Long id);

    void delete(Long id);

    boolean isUserWithRoleExists(Role role);
}
