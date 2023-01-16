package dev.curly.pp_3_1_5.service;

import dev.curly.pp_3_1_5.exceptions.UserEmailAlreadyInUse;
import dev.curly.pp_3_1_5.model.Role;
import dev.curly.pp_3_1_5.model.User;
import dev.curly.pp_3_1_5.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getAll() {
        return userRepo.findAll();
    }

    @Override
    public Optional<User> findById(long id) {
        return userRepo.findById(id);
    }

    @Override
    @Transactional
    public void add(User user) {
        if (userRepo.findByEmail(user.getEmail()).isPresent()) {
            throw new UserEmailAlreadyInUse(user.getEmail());
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
    }

    @Override
    @Transactional
    public User update(User user) {
        var rawPassword = user.getPassword();

        if (rawPassword != null && !rawPassword.isEmpty()) {
            // set password from frontend, if present
            user.setPassword(passwordEncoder.encode(rawPassword));
        } else {
            // set password from saved entity, if present
            userRepo.findById(user.getId())
                    .ifPresent(dbUser -> user.setPassword(dbUser.getPassword()));
        }

        return userRepo.save(user);
    }

    @Override
    @Transactional
    public void delete(long id) {
        userRepo.deleteById(id);
    }

    @Override
    public boolean isUserWithRoleExists(Role role) {
        return userRepo.existsWithRole(role.getName());
    }
}
