package org.example.user_management_application.service;

import jakarta.validation.Valid;
import org.example.user_management_application.model.dto.UserDTO;
import org.example.user_management_application.model.entity.User;
import org.example.user_management_application.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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

    public Optional<UserDTO> checkIfUserWithGivenEmailExist(@Valid UserDTO newUser) {
        return userRepository
                .findUserByEmail(newUser.getEmail())
                .map(user -> modelMapper.map(user, UserDTO.class));
    }


/*
    public void updateUser(Long id, UserDTO user) {

        UserDTO userById = userRepository.findUserById(id);

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


    public boolean isUserExist(UserDTO userBindingModel) {
        UserDTO userById = userRepository.findUserById(userBindingModel.getId());
        return userById != null;
    }


    public boolean userExist(Long id) {
        Optional<UserDTO> byId = userRepository.findById(id);
        return byId.isPresent();
    }


    public List<UserDTO> search(String item) {
        return userRepository.findUsersBySearchedItem(item);
    }
*/

}
