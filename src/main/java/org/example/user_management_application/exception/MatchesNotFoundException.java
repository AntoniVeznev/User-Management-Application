package org.example.user_management_application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class MatchesNotFoundException extends RuntimeException{

    public MatchesNotFoundException() {
        super("Matches not found!");
    }

}
