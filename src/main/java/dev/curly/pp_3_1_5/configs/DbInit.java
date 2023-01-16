package dev.curly.pp_3_1_5.configs;

import dev.curly.pp_3_1_5.exceptions.UserEmailAlreadyInUse;
import dev.curly.pp_3_1_5.model.Role;
import dev.curly.pp_3_1_5.model.User;
import dev.curly.pp_3_1_5.service.RoleService;
import dev.curly.pp_3_1_5.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DbInit {
    private static final Logger log = LoggerFactory.getLogger(DbInit.class);
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public DbInit(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    /**
     * Add admin user if no admins present in db
     */
    @Bean
    CommandLineRunner init() throws UserEmailAlreadyInUse {
        if (userService.isUserWithRoleExists(Role.adminRole())) {
            log.warn("Admin user already exists. Stopping db init");

            return null;
        }

        return args -> {
            // Add 2 roles
            var adminRole = Role.adminRole();
            var userRole = Role.userRole();
            roleService.add(adminRole);
            log.info("Add ADMIN role");
            roleService.add(userRole);
            log.info("Add USER role");

            // Add admin
            var admin = new User();
            admin.setFirstName("Admin");
            admin.setLastName("Foo");
            admin.setPassword("admin");
            admin.setEmail("admin@local.test");
            admin.setAge(30);
            admin.setRoles(List.of(adminRole, userRole));
            userService.add(admin);
            log.info("Add Admin");

            // Add user
            var user = new User();
            user.setFirstName("User");
            user.setLastName("Bar");
            user.setPassword("user");
            user.setEmail("user@local.test");
            user.setAge(25);
            user.setRoles(List.of(userRole));
            userService.add(user);
            log.info("Add User");
        };
    }
}
