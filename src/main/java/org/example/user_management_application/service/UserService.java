package org.example.user_management_application.service;

import org.example.user_management_application.model.dto.UserDTO;
import org.example.user_management_application.model.entity.User;
import org.example.user_management_application.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public List<UserDTO> getAllUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .toList();

    }

    public List<UserDTO> getAllUsersSorted() {
        return userRepository
                .getAllUsersSorted()
                .stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .toList();

    }

    public Optional<UserDTO> getUserById(Long userId) {
        return userRepository
                .findById(userId)
                .map(user -> modelMapper.map(user, UserDTO.class));

    }

    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);

    }


    public Long createUser(UserDTO userDTO) {
        User newUser = modelMapper.map(userDTO, User.class);
        return userRepository.save(newUser).getId();

    }

    public Optional<UserDTO> checkIfUserWithGivenEmailExist(UserDTO newUser) {
        return userRepository
                .findUserByEmail(newUser.getEmail())
                .map(user -> modelMapper.map(user, UserDTO.class));

    }


    public void updateUser(Long userId, UserDTO updatedUser) {

        User userById = userRepository.findUserById(userId);

        String firstName = updatedUser.getFirstName();
        String lastName = updatedUser.getLastName();
        LocalDate dateOfBirth = updatedUser.getDateOfBirth();
        String email = updatedUser.getEmail();
        String phoneNumber = updatedUser.getPhoneNumber();

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

        userRepository.save(userById);

    }

    public List<UserDTO> searchUser(String item) {
        return userRepository.findUsersBySearchedItem(item);

    }

}

