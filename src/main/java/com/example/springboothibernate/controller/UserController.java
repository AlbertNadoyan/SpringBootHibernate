package com.example.springboothibernate.controller;

import com.example.springboothibernate.model.User;
import com.example.springboothibernate.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getUsers(Model model){
        model.addAttribute("users", userService.getUsers());
        return "users";
    }

    @GetMapping("/add")
    public String addUserPage(Model model){
        model.addAttribute("user", new User());
        return "addEditUser";
    }

    @PostMapping
    public String createUser(@ModelAttribute User user){
        userService.addUser(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}")
    public String viewUserById(@PathVariable Long id, Model model){
        User user = userService.getUserById(id).orElseThrow(() -> new RuntimeException("User not found"));
        model.addAttribute("user", user);
        return "viewUser";
    }

    @GetMapping("/{id}/edit")
    public String editUserPage(@PathVariable Long id, Model model){
        User user = userService.getUserById(id).orElseThrow(() -> new RuntimeException("User not found"));
        model.addAttribute("user", user);
        return "addEditUser";
    }

    @PostMapping("/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute User user){
        userService.updateUser(id, user);
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return "redirect:/users";
    }
}
