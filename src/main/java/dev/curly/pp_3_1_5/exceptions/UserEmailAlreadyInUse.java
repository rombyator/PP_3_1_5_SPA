package dev.curly.pp_3_1_5.exceptions;

public class UserEmailAlreadyInUse extends RuntimeException {
    public UserEmailAlreadyInUse(String email) {
        super("This email is already in use: " + email);
    }
}
