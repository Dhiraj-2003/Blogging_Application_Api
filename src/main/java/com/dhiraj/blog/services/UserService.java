package com.dhiraj.blog.services;

import com.dhiraj.blog.entities.User;
import com.dhiraj.blog.payloads.UserDto;

import java.util.List;

public interface UserService {
    UserDto registerNewUser(UserDto userDto);
    UserDto createUser(UserDto userDto);
    UserDto updateUser(UserDto userDto,Integer uid);
    UserDto getUserById(Integer uid);
    List<UserDto> getAllUsers();
    void deleteUser(Integer uid);
}
