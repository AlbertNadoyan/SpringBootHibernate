package com.example.springboothibernate.controller;

import com.example.springboothibernate.exception.UserNotFountException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(UserNotFountException.class)
    public String handleUserNotFoundException(UserNotFountException e, Model model){
        model.addAttribute("errorMessage", e.getMessage());
        return "error";
    }

    @ExceptionHandler(Exception.class)
    public String handleGenericException(Exception e, Model model){
        model.addAttribute("errorMessage", "An unexpected error occurred " + e.getMessage());
        return "error";
    }
}
