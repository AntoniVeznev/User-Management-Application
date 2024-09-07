package org.example.user_management_application.web;

import jakarta.validation.Valid;
import org.example.user_management_application.exception.EmptyDatabaseException;
import org.example.user_management_application.exception.MatchesNotFoundException;
import org.example.user_management_application.exception.UserAlreadyExistException;
import org.example.user_management_application.exception.UserNotFoundException;
import org.example.user_management_application.model.dto.UserDTO;
import org.example.user_management_application.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/get/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {

        List<UserDTO> allUsers = userService.getAllUsers();

        if (allUsers.isEmpty()) {
            throw new EmptyDatabaseException();
        }

        return ResponseEntity.ok(allUsers);

    }

    @GetMapping("/get/sorted/users")
    public ResponseEntity<List<UserDTO>> getAllUsersSorted() {

        List<UserDTO> allUsers = userService.getAllUsersSorted();

        if (allUsers.isEmpty()) {
            throw new EmptyDatabaseException();
        }

        return ResponseEntity.ok(allUsers);

    }

    @GetMapping("/get/user/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long userId) {

        Optional<UserDTO> userById = userService.getUserById(userId);

        return userById.map(ResponseEntity::ok)
                .orElseThrow(UserNotFoundException::new);

    }


    @DeleteMapping("/delete/user/{id}")
    public ResponseEntity<UserDTO> deleteUserById(@PathVariable("id") Long userId) {

        boolean userExist = userService.getUserById(userId).isPresent();

        if (!userExist) {
            throw new UserNotFoundException();
        }

        userService.deleteUserById(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }


    @PostMapping("/post/create/user")
    public ResponseEntity<UserDTO> createUser(@RequestBody @Valid UserDTO newUser, UriComponentsBuilder uriComponentsBuilder) {

        boolean userExist = userService.checkIfUserWithGivenEmailExist(newUser).isPresent();

        if (userExist) {
            throw new UserAlreadyExistException();
        }

        Long newUserId = userService.createUser(newUser);
        return ResponseEntity.created(uriComponentsBuilder.path("/api/user/{id}").build(newUserId)).build();

    }


    @PatchMapping("/patch/update/user/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable("id") Long userId, @RequestBody UserDTO updatedUser) {

        boolean userExist = userService.getUserById(userId).isPresent();

        if (!userExist) {
            throw new UserNotFoundException();
        }

        userService.updateUser(userId, updatedUser);
        return ResponseEntity.ok(updatedUser);

    }

    @GetMapping("/get/search/{item}")
    public ResponseEntity<List<UserDTO>> searchUser(@PathVariable("item") String item) {

        List<UserDTO> foundMatches = userService.searchUser(item);

        if (foundMatches.isEmpty()) {
            throw new MatchesNotFoundException();
        }

        return ResponseEntity.ok(foundMatches);

    }
}
