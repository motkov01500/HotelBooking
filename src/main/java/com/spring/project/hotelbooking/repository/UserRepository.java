package com.spring.project.hotelbooking.repository;

import com.spring.project.hotelbooking.dto.UserDTO;
import com.spring.project.hotelbooking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    @Query("FROM User U RIGHT JOIN U.loyalty LO WHERE LO.name = :loyaltyName")
    List<User> getUsersByType(@Param("loyaltyName") String loyaltyName);

    @Query("FROM User U WHERE U.email = :email")
    User getUserByEmail(@Param("email") String email);
}
