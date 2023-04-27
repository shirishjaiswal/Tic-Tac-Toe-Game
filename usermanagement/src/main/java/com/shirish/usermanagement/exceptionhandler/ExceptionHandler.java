package com.shirish.usermanagement.exceptionhandler;

import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler (MethodArgumentNotValidException.class)
    public Map<String, String> exceptionHandle (MethodArgumentNotValidException e) {
        Map<String, String> eMap = new HashMap<>();
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        for (ObjectError error : allErrors) {
            String code = error.getCode();
            String defaultMessage = error.getDefaultMessage();
            eMap.put(code, defaultMessage);
        }
        return  eMap;
    }
}
