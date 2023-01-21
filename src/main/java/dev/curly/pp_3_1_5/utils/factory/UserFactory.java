package dev.curly.pp_3_1_5.utils.factory;

import dev.curly.pp_3_1_5.model.User;

import java.util.List;

public class UserFactory {
    public static User anonymousUser() {
        var user = new User();
        user.setId(-1);
        user.setFirstName("Anonymous");
        user.setLastName("");
        user.setAge(0);
        user.setEmail("no email");
        user.setRoles(List.of());

        return user;
    }

    public static User newUser() {
        var user = new User();
        user.setFirstName("");
        user.setLastName("");
        user.setAge(0);
        user.setEmail("");
        user.setRoles(List.of());

        return user;
    }

}
