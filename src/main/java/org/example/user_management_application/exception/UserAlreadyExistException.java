package org.example.user_management_application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserAlreadyExistException extends ResponseStatusException {
    public UserAlreadyExistException(HttpStatusCode status, String reason) {
        super(status, reason);
    }
}

