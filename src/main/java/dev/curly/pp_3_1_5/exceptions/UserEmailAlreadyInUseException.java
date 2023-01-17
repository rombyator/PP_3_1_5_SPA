package dev.curly.pp_3_1_5.exceptions;

public class UserEmailAlreadyInUseException extends RuntimeException {
    public UserEmailAlreadyInUseException(String email) {
        super("This email is already in use: " + email);
    }
}
