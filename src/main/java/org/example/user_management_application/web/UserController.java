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

    //TODO: (РАБОТИ)
    // Вади всички Юзъри от базата - връща код "Oк" (200).
    // Ако листа е празен връща - код "No Content" (204).
    // Листа е подреден 1-во по Фамилно име и 2-ро по Дата на раждане.
    @GetMapping("/api/users")
    public ResponseEntity<List<User>> getUsers() {

        List<User> allUser = userService.getAllUser();

        if (allUser.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(allUser);

    }

    //TODO: (РАБОТИ)
    // Показва конкретен Юзър по ID от базата - връща код "Oк" (200).
    // Ако юзъра не съществува връща - код "No Content" (204).
    @GetMapping("/api/user/{userId}")
    public ResponseEntity<Optional<User>> getUser(@PathVariable Long userId) {

        Optional<User> userById = userService.getUserById(userId);

        if (userById.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(userById);
    }

    //TODO: (РАБОТИ)
    // Създава Юзър като подаваме неговото ИД, Първо име, Последно име, Дата на раждане(формат - 2007-12-03 година-месец-ден), Телефон и Имейл.
    // Ако няма такъв обект в базата го създава и връща код "Created" (201).
    // Ако има такъв обект връща код "IM Used" (226).
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

    //TODO: (РАБОТИ)
    // Трие Юзър при подадено ID.
    // Ако Юзъра съществува в базата се изтрива и връща код "No Content" (204).
    // Ако го няма в базата връща код "Not Found" (404).
    @DeleteMapping("/api/delete/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {

        boolean exist = userService.userExist(id);

        if (exist) {
            userService.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    //TODO: (РАБОТИ) Ъпдейтва Юзър при подадено ID.
    // Ако Юзъра съществува в базата връща код "Ок" (200).
    // Ако го няма в базата връща код "Not Found" (404).
    @PutMapping("/api/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {

        boolean exist = userService.userExist(id);

        if (exist) {
            userService.updateUser(id, user);
            return ResponseEntity.ok(user);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    //TODO: SEARCH FUNCTIONALITY (работи) ако е празен 204 но контент . ако мачва код 200 окау
    @GetMapping("api/search/{item}")
    public ResponseEntity<List<User>> searchUser(@PathVariable String item) {

        List<User> foundMatches = userService.search(item);

        if (foundMatches.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(foundMatches);

    }
}
