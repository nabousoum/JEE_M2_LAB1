package com.dev.services.mapping;

import com.dev.services.dto.UserDto;
import com.dev.services.entities.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    UserDto toUser(User user);
    User fromUser(UserDto userDto);
}
