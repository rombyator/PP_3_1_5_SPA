package dev.curly.pp_3_1_5.service;

import dev.curly.pp_3_1_5.exceptions.UserEmailAlreadyInUse;
import dev.curly.pp_3_1_5.model.Role;
import dev.curly.pp_3_1_5.model.User;

import java.util.List;

public interface UserService {
    List<User> getAll();

    void add(User user) throws UserEmailAlreadyInUse;

    User getById(long id);

    void update(User user);

    void delete(long id);

    boolean isUserWithRoleExists(Role role);
}
