package ru.kozhevnikov.jwtauth.service.mapper;

import org.mapstruct.Mapper;
import ru.kozhevnikov.jwtauth.data.model.User;
import ru.kozhevnikov.jwtauth.dto.UserResponseDto;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponseDto toDto(User entity);
}
