package dev.curly.pp_3_1_5.service;

import dev.curly.pp_3_1_5.exceptions.UserEmailAlreadyInUseException;
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
    public Optional<User> findById(Long id) {
        return userRepo.findById(id);
    }

    @Override
    @Transactional
    public User add(User newUser) {
        // Check if user is already in db
        Optional<User> maybeSavedUser = userRepo.findByEmail(newUser.getEmail());
        if (maybeSavedUser.isPresent()) {
            throw new UserEmailAlreadyInUseException(newUser.getEmail());
        }

        encodePassword(newUser);

        return userRepo.save(newUser);
    }

    @Override
    @Transactional
    public User update(User newUser, Long id) {
        encodePassword(newUser);

        return userRepo.findById(id).map(dbUser -> {
            if (newUser.getPassword().isEmpty()) {
                newUser.setPassword(dbUser.getPassword());
            }
            dbUser.updateWith(newUser);

            return userRepo.save(dbUser);
        }).orElseGet(() -> {
            newUser.setId(id);

            return userRepo.save(newUser);
        });
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userRepo.deleteById(id);
    }

    @Override
    public boolean isUserWithRoleExists(Role role) {
        return userRepo.existsWithRole(role.getName());
    }

    private void encodePassword(User user) {
        var rawPassword = user.getPassword().trim();
        if (!rawPassword.isEmpty()) {
            user.setPassword(passwordEncoder.encode(rawPassword));
        }
    }
}
