package org.example.user_management_application.service.Impl;

import org.example.user_management_application.model.dto.UserBindingModel;
import org.example.user_management_application.model.entity.User;
import org.example.user_management_application.repository.UserRepository;
import org.example.user_management_application.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.giveMeAllUsers();
    }

    @Override
    public User createUser(UserBindingModel userBindingModel) {

        String date = userBindingModel.getDateOfBirth();
        LocalDate convertedDate = LocalDate.parse(date);

        User user = modelMapper.map(userBindingModel, User.class);
        user.setDateOfBirth(convertedDate);
        userRepository.save(user);

        return user;
    }


    @Override
    public void deleteUser(Long id) {
        Optional<User> byId = userRepository.findById(id);
        if (byId.isPresent()) {
            userRepository.deleteById(id);
        }
    }

    @Override
    public void updateUser(Long id, User user) {

        User userById = userRepository.findUserById(id);

        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        LocalDate dateOfBirth = user.getDateOfBirth();
        String email = user.getEmail();
        String phoneNumber = user.getPhoneNumber();

        if (firstName != null) {
            userById.setFirstName(firstName);
        }
        if (lastName != null) {
            userById.setLastName(lastName);
        }
        if (dateOfBirth != null) {
            userById.setDateOfBirth(dateOfBirth);
        }
        if (email != null) {
            userById.setEmail(email);
        }
        if (phoneNumber != null) {
            userById.setPhoneNumber(phoneNumber);
        }

        userById.setId(id);
        userRepository.save(userById);

    }

    @Override
    public boolean isUserExist(UserBindingModel userBindingModel) {
        User userById = userRepository.findUserById(userBindingModel.getId());
        return userById != null;
    }

    @Override
    public boolean userExist(Long id) {
        Optional<User> byId = userRepository.findById(id);
        return byId.isPresent();
    }

    @Override
    public List<User> search(String item) {
        return userRepository.findUsersBySearchedItem(item);
    }
}
