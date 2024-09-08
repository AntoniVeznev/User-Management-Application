package org.example.user_management_application.service;

import org.example.user_management_application.exception.EmptyDatabaseException;
import org.example.user_management_application.exception.MatchesNotFoundException;
import org.example.user_management_application.exception.UserAlreadyExistException;
import org.example.user_management_application.exception.UserNotFoundException;
import org.example.user_management_application.model.dto.UserDTO;
import org.example.user_management_application.model.entity.User;
import org.example.user_management_application.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
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
        List<User> allUsers = userRepository.findAll();
        if (allUsers.isEmpty()) {
            throw new EmptyDatabaseException(HttpStatus.NO_CONTENT, "The Database is empty!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        } else {
            return allUsers.stream().map(user -> modelMapper.map(user, UserDTO.class)).toList();
        }
    }

    public List<UserDTO> getAllUsersSorted() {
        List<User> allSortedUsers = userRepository.getAllUsersSorted();
        if (allSortedUsers.isEmpty()) {
            throw new EmptyDatabaseException(HttpStatus.NO_CONTENT, "The Database is empty!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        } else {
            return allSortedUsers.stream().map(user -> modelMapper.map(user, UserDTO.class)).toList();
        }
    }

    public UserDTO getUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(HttpStatus.NOT_FOUND, "User not found!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"));
        return modelMapper.map(user, UserDTO.class);
    }

    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }


    public Long createUser(UserDTO userDTO) {
        User newUser = modelMapper.map(userDTO, User.class);
        return userRepository.save(newUser).getId();
    }

    public void checkIfUserWithGivenEmailExist(UserDTO newUser) {
        Optional<User> userByEmail = userRepository.findUserByEmail(newUser.getEmail());
        if (userByEmail.isPresent()) {
            throw new UserAlreadyExistException(HttpStatus.CONFLICT, "User already exists!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        }
    }

    public List<UserDTO> searchUser(String item) {
        List<User> allUsersBySearchedItem = userRepository.findUsersBySearchedItem(item);
        if (allUsersBySearchedItem.isEmpty()) {
            throw new MatchesNotFoundException(HttpStatus.NO_CONTENT, "Matches not found!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        } else {
            return allUsersBySearchedItem.stream().map(user -> modelMapper.map(user, UserDTO.class)).toList();
        }
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
}
