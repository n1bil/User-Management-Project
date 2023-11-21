package net.example.springboot.mapper;

import net.example.springboot.dto.UserDto;
import net.example.springboot.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoUserMapper {

    AutoUserMapper MAPPER = Mappers.getMapper(AutoUserMapper.class);

    @Mapping(source = "email", target = "emailAddress")
    UserDto mapToUserDto(User user);

    @Mapping(source = "emailAddress", target = "email")
    User mapToUser(UserDto userDto);

}
