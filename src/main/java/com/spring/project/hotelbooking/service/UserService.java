package com.spring.project.hotelbooking.service;

import com.spring.project.hotelbooking.dto.UserDTO;
import com.spring.project.hotelbooking.entity.Loyalty;
import com.spring.project.hotelbooking.entity.User;
import com.spring.project.hotelbooking.exception.UserNotFoundException;
import com.spring.project.hotelbooking.mapper.UserMapper;
import com.spring.project.hotelbooking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final LoyaltyService loyaltyService;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, LoyaltyService loyaltyService, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.loyaltyService = loyaltyService;
        this.userMapper = userMapper;
    }

    public List<UserDTO> getAllUsers() {
        List<User> allUsers = userRepository.findAll();
        return allUsers
                .stream()
                .map(userMapper::userToUserDTO)
                .toList();
    }

    public User getUserById(int id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found!"));
    }

    public List<UserDTO> getUsersByType(String userType) {
        List<User> users = userRepository.getUsersByType(userType);
        return users
                .stream()
                .map(userMapper::userToUserDTO)
                .toList();
    }

    public void updateLoyaltyByCountOfReservations(int reservationsCount, int userId) {
        if(reservationsCount > 3  && reservationsCount < 6) {
            updateLoyaltyType("GOLD", userId);
        }
        if(reservationsCount > 6) {
            updateLoyaltyType("PLATINUM", userId);
        }
    }

    public UserDTO updateLoyaltyType(String loyaltyName, int userId) {
        User user = userRepository
                .findById(userId)
                .orElseThrow(()-> new UserNotFoundException("This user is not found"));
        Loyalty loyalty = loyaltyService.getLoyaltyTypeByName(loyaltyName);
        user.setLoyalty(loyalty);
        userRepository.save(user);
        return userMapper.userToUserDTO(user);
    }
}
