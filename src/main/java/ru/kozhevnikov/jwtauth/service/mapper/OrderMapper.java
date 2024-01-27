package ru.kozhevnikov.jwtauth.service.mapper;

import org.mapstruct.Mapper;
import ru.kozhevnikov.jwtauth.data.model.Order;
import ru.kozhevnikov.jwtauth.dto.OrderResponseDto;

@Mapper(componentModel = "spring")
public interface OrderMapper{

    OrderResponseDto toDto(Order entity);
}
