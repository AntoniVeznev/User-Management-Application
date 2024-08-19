package org.example.user_management_application.service;

import org.example.user_management_application.model.dto.UserBindingModel;
import org.example.user_management_application.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> getUserById(Long userId);

    List<User> getAllUser();

    User createUser(UserBindingModel userBindingModel);

    void deleteUser(Long id);

    void updateUser(Long id, User user);

}
