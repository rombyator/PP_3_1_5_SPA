package dev.curly.pp_3_1_5.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserEmailAlreadyInUseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorMessage emailAlreadyInUseHandler(UserEmailAlreadyInUseException ex) {
        return new ErrorMessage(ex.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ErrorMessage userNotFoundHandler(UserNotFoundException ex) {
        return new ErrorMessage(ex.getMessage());
    }
}
