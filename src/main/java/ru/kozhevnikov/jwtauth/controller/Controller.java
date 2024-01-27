package ru.kozhevnikov.jwtauth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.kozhevnikov.jwtauth.dto.*;
import ru.kozhevnikov.jwtauth.service.JwtService;
import ru.kozhevnikov.jwtauth.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("main")
public class Controller {

    private final UserService userService;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<OrdersResponseDto> getOrders(@PathVariable Long id){
        List<OrderResponseDto> orders = userService.getOrders(id);
        OrdersResponseDto responseDto = new OrdersResponseDto(orders);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<UsersResponseDto> getUsers(){
        List<UserResponseDto> users = userService.getUsers();
        UsersResponseDto responseDto = new UsersResponseDto(users);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody AuthRequestDto authRequest){
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authenticate.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new BadCredentialsException("Неправильный логин или пароль");
        }
    }
}
