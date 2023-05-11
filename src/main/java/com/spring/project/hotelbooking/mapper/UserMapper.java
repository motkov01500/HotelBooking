package com.spring.project.hotelbooking.mapper;

import com.spring.project.hotelbooking.dto.UserDTO;
import com.spring.project.hotelbooking.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO userToUserDTO(User user);
}
