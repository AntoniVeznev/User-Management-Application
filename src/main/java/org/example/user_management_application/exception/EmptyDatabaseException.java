package org.example.user_management_application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class EmptyDatabaseException extends RuntimeException {

    public EmptyDatabaseException() {
        super("No users in the Database!");
    }

}
