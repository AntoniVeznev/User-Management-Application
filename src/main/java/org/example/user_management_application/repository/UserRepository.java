package org.example.user_management_application.repository;

import org.example.user_management_application.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByEmail(String email);

    User findUserById(Long userId);

    @Query("SELECT u FROM User u order by u.lastName, u.dateOfBirth")
    List<User> getAllUsersSorted();

    @Query("SELECT u FROM User u where u.firstName =?1 or u.lastName=?1 or u.email =?1 or u.phoneNumber=?1")
    List<User> findUsersBySearchedItem(String item);

}

