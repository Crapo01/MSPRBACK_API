package com.capus.cms_nationsound.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.capus.cms_nationsound.dto.responses.MyHttpResponse.response;

@RestControllerAdvice
public class AppExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> resourceNotFoundException(ResourceNotFoundException ex) {
        return response(HttpStatus.NOT_FOUND,ex.getMessage(),null);
    }
}
