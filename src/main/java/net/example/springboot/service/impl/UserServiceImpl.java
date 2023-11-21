package net.example.springboot.service.impl;

import lombok.AllArgsConstructor;
import net.example.springboot.dto.UserDto;
import net.example.springboot.entity.User;
import net.example.springboot.exception.EmailAlreadyExistsException;
import net.example.springboot.exception.ResourceNofFoundException;
import net.example.springboot.mapper.AutoUserMapper;
import net.example.springboot.repository.UserRepository;
import net.example.springboot.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        // Convert UserDto into User JPA Entity
//        User user = modelMapper.map(userDto, User.class);

        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmailAddress());

        if (optionalUser.isPresent()) {
            throw new EmailAlreadyExistsException("Email already Exists for User");
        }

        User user = AutoUserMapper.MAPPER.mapToUser(userDto);

        User savedUser = userRepository.save(user);

        // Convert User JPA entity to UserDto
//        return modelMapper.map(savedUser, UserDto.class);
        return AutoUserMapper.MAPPER.mapToUserDto(savedUser);
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new ResourceNofFoundException("User", "id", userId));

//        return modelMapper.map(user, UserDto.class);
        return AutoUserMapper.MAPPER.mapToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();

        return users.stream()
//                .map((user -> modelMapper.map(user, UserDto.class))
                .map(AutoUserMapper.MAPPER::mapToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto user, Long id) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new ResourceNofFoundException("User", "id", id));
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmailAddress());

        User updatedUser = userRepository.save(existingUser);

//        return modelMapper.map(updatedUser, UserDto.class);
        return AutoUserMapper.MAPPER.mapToUserDto(updatedUser);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.findById(userId).orElseThrow(() -> new ResourceNofFoundException("User", "id", userId));

        userRepository.deleteById(userId);
    }
}
