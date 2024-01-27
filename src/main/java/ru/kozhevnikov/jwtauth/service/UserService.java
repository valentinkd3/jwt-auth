package ru.kozhevnikov.jwtauth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kozhevnikov.jwtauth.config.CustomUserDetails;
import ru.kozhevnikov.jwtauth.data.model.User;
import ru.kozhevnikov.jwtauth.data.repository.UserRepository;
import ru.kozhevnikov.jwtauth.dto.OrderResponseDto;
import ru.kozhevnikov.jwtauth.dto.UserResponseDto;
import ru.kozhevnikov.jwtauth.exception.UserNotFoundException;
import ru.kozhevnikov.jwtauth.service.mapper.OrderMapper;
import ru.kozhevnikov.jwtauth.service.mapper.UserMapper;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final OrderMapper orderMapper;

    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(CustomUserDetails::new)
                .orElseThrow(() -> new BadCredentialsException("Неверное имя пользователя"));
    }

    public List<OrderResponseDto> getOrders(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Пользователь не найден"));
        return user.getBasket().stream()
                .map(orderMapper::toDto)
                .toList();
    }

    public List<UserResponseDto> getUsers(){
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }
}
