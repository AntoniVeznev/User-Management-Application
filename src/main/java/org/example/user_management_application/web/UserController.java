package org.example.user_management_application.web;

import org.example.user_management_application.exception.EmptyDatabaseException;
import org.example.user_management_application.exception.MatchesNotFoundException;
import org.example.user_management_application.exception.UserAlreadyExistException;
import org.example.user_management_application.exception.UserNotFoundException;
import org.example.user_management_application.model.dto.UserBindingModel;
import org.example.user_management_application.model.entity.User;
import org.example.user_management_application.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {

        List<User> allUsers = userService.getAllUsers();

        if (allUsers.isEmpty()) {
            throw new EmptyDatabaseException();
        }
        return ResponseEntity.ok(allUsers);

    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Optional<User>> getUser(@PathVariable Long userId) {

        Optional<User> userById = userService.getUserById(userId);

        if (userById.isEmpty()) {
            throw new UserNotFoundException();
        }
        return ResponseEntity.ok(userById);
    }

    @PostMapping("/create/{id}/{firstName}/{lastName}/{dateOfBirth}/{phoneNumber}/{email}")
    public ResponseEntity<User> createUser(@PathVariable Long id,
                                           @PathVariable String firstName,
                                           @PathVariable String lastName,
                                           @PathVariable String dateOfBirth,
                                           @PathVariable String phoneNumber,
                                           @PathVariable String email) {

        UserBindingModel userBindingModel = new UserBindingModel();

        userBindingModel
                .setId(id)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setDateOfBirth(dateOfBirth)
                .setPhoneNumber(phoneNumber)
                .setEmail(email);

        boolean existOrNot = userService.isUserExist(userBindingModel);

        if (existOrNot) {
            throw new UserAlreadyExistException();
        }

        User user = userService.createUser(userBindingModel);
        return new ResponseEntity<>(user, HttpStatus.CREATED);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {

        boolean exist = userService.userExist(id);

        if (!exist) {
            throw new UserNotFoundException();
        }

        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {

        boolean exist = userService.userExist(id);

        if (!exist) {
            throw new UserNotFoundException();
        }

        userService.updateUser(id, user);
        return ResponseEntity.ok(user);

    }

    @GetMapping("/search/{item}")
    public ResponseEntity<List<User>> searchUser(@PathVariable String item) {

        List<User> foundMatches = userService.search(item);

        if (foundMatches.isEmpty()) {
            throw new MatchesNotFoundException();
        }

        return ResponseEntity.ok(foundMatches);

    }
}
