package org.example.user_management_application.web;

import jakarta.validation.Valid;
import org.example.user_management_application.model.dto.UserDTO;
import org.example.user_management_application.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/get/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/get/sorted/users")
    public ResponseEntity<List<UserDTO>> getAllUsersSorted() {
        return ResponseEntity.ok(userService.getAllUsersSorted());
    }

    @GetMapping("/get/user/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @DeleteMapping("/delete/user/{id}")
    public ResponseEntity<UserDTO> deleteUserById(@PathVariable("id") Long userId) {
        userService.getUserById(userId);
        userService.deleteUserById(userId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/post/create/user")
    public ResponseEntity<UserDTO> createUser(@RequestBody @Valid UserDTO newUser, UriComponentsBuilder uriComponentsBuilder) {
        userService.checkIfUserWithGivenEmailExist(newUser);
        Long newUserId = userService.createUser(newUser);
        return ResponseEntity.created(uriComponentsBuilder.path("/api/user/{id}").build(newUserId)).build();
    }

    @PatchMapping("/patch/update/user/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable("id") Long userId, @RequestBody UserDTO updatedUser) {
        userService.getUserById(userId);
        userService.updateUser(userId, updatedUser);
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping("/get/search/{item}")
    public ResponseEntity<List<UserDTO>> searchUser(@PathVariable("item") String item) {
        return ResponseEntity.ok(userService.searchUser(item));
    }
}
