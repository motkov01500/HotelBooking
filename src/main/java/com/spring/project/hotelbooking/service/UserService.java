package com.spring.project.hotelbooking.service;

import com.spring.project.hotelbooking.entity.User;
import com.spring.project.hotelbooking.exception.UserNotFoundException;
import com.spring.project.hotelbooking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(int id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found!"));
    }
}
