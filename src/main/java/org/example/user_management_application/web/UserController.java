package org.example.user_management_application.web;

import org.example.user_management_application.model.dto.UserBindingModel;
import org.example.user_management_application.model.entity.User;
import org.example.user_management_application.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //TODO: (РАБОТИ) Вади всички Юзъри от базата.
    @GetMapping("/api/users")
    public ResponseEntity<List<User>> getUsers() {

        List<User> allUser = userService.getAllUser();

        if (allUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(allUser);
    }

    //TODO: (РАБОТИ) Вади конкретен Юзър по ID от базата.
    @GetMapping("/api/user/{userId}")
    public ResponseEntity<Optional<User>> getUser(@PathVariable Long userId) {

        Optional<User> userById = userService.getUserById(userId);

        if (userById.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(userById);
    }

    //TODO: (РАБОТИ)
    //TODO: CREATE USER !!! Създава Юзър като подаваме неговото ИД, Първо име, Последно име, Дата на раждане(формат - 2007-12-03 година-месец-ден), Телефон и Имейл.
    @PostMapping("/api/create/{id}/{firstName}/{lastName}/{dateOfBirth}/{phoneNumber}/{email}")
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
        if (!existOrNot) {
            User user = userService.createUser(userBindingModel);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.IM_USED);

    }

    //TODO: (РАБОТИ) Трие Юзър при подадено ID.
    @DeleteMapping("/api/delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);

    }

    //TODO: (РАБОТИ) Ъпдейтва Юзър при подадено ID.
    @PutMapping("/api/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        userService.updateUser(id, user);
        return ResponseEntity.ok(user);
    }

}
