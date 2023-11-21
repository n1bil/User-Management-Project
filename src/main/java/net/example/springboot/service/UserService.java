package net.example.springboot.service;

import net.example.springboot.dto.UserDto;
import net.example.springboot.entity.User;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto user);

    UserDto getUserById(Long userId);

    List<UserDto> getAllUsers();

    UserDto updateUser(UserDto user, Long id);

    void deleteUser(Long userId);
}
