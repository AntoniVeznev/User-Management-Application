package org.example.user_management_application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends ResponseStatusException {
    public UserNotFoundException(HttpStatusCode status, String reason) {
        super(status, reason);
    }
}

