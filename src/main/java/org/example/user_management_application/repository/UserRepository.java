package org.example.user_management_application.repository;

import org.example.user_management_application.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u order by u.lastName, u.dateOfBirth")
    List<User> giveMeAllUsers();

    User findUserById(Long id);
}
