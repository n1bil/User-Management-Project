package net.example.springboot.service;

import net.example.springboot.entity.User;

import java.util.List;

public interface UserService {

    User createUser(User user);

    User getUserById(Long userId);

    List<User> getAllUsers();

    User updateUser(User user, Long id);

    void deleteUser(Long userId);
}
