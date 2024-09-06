package org.example.user_management_application.model.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class UserDTO {

    public String firstName;
    public String lastName;
    public LocalDate dateOfBirth;
    public String phoneNumber;
    public String email;

    public UserDTO() {

    }

    @NotEmpty
    public String getFirstName() {
        return firstName;
    }

    @NotEmpty
    public String getLastName() {
        return lastName;
    }

    @Past
    @NotNull
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    @NotEmpty
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Email
    @NotEmpty
    public String getEmail() {
        return email;
    }

    public UserDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserDTO setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public UserDTO setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public UserDTO setEmail(String email) {
        this.email = email;
        return this;
    }
}
