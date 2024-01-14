package com.spring.project.hotelbooking.controller;

import com.spring.project.hotelbooking.dto.UserDTO;
import com.spring.project.hotelbooking.entity.User;
import com.spring.project.hotelbooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("getUsersByType")
    public ResponseEntity<List<UserDTO>> getUsersByType(@RequestParam String userType) {
        List<UserDTO> users = userService.getUsersByType(userType);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("getUsers")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
