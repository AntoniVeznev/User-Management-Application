package org.example.user_management_application.model.dto;


import jakarta.validation.constraints.NotNull;

public class UserBindingModel {

    public Long id;
    public String firstName;
    public String lastName;
    public String dateOfBirth;
    public String phoneNumber;
    public String email;

    public UserBindingModel() {

    }

    @NotNull
    public Long getId() {
        return id;
    }

    public UserBindingModel setId(Long id) {
        this.id = id;
        return this;
    }

    @NotNull
    public String getFirstName() {
        return firstName;
    }

    public UserBindingModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    @NotNull
    public String getLastName() {
        return lastName;
    }

    public UserBindingModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    @NotNull
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public UserBindingModel setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    @NotNull
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UserBindingModel setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    @NotNull
    public String getEmail() {
        return email;
    }

    public UserBindingModel setEmail(String email) {
        this.email = email;
        return this;
    }
}