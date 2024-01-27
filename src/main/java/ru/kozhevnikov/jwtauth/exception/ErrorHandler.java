package ru.kozhevnikov.jwtauth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.kozhevnikov.jwtauth.dto.ErrorResponseDto;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler
    private ResponseEntity<ErrorResponseDto> exceptionHandler(UserNotFoundException e){
        ErrorResponseDto errorResponse = new ErrorResponseDto(e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponseDto> exceptionHandler(BadCredentialsException e){
        ErrorResponseDto errorResponse = new ErrorResponseDto(e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }
}
