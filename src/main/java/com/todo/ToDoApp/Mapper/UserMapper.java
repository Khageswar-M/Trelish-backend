package com.todo.ToDoApp.Mapper;

import com.todo.ToDoApp.DTO.UserDto;
import com.todo.ToDoApp.Entity.User;

import java.util.UUID;

public class UserMapper {
    public static UserDto mapToUserDto(User user){
        return new UserDto(
                user.getUserId(),
                user.getPassword(),
                user.getEmail()
        );
    }

    public static User mapToUser(UserDto userDto){
        User user = new User();
        user.setUserId(UUID.randomUUID().toString());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());

        return user;
    }
}
