package dev.curly.pp_3_1_5.utils.factory;

import dev.curly.pp_3_1_5.model.Role;

public class RoleFactory {
    public static Role adminRole() {
        return new Role("admin");
    }

    public static Role userRole() {
        return new Role("user");
    }
}
