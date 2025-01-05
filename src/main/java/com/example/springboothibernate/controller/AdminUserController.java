package com.example.springboothibernate.controller;

import com.example.springboothibernate.model.Role;
import com.example.springboothibernate.model.User;
import com.example.springboothibernate.service.RoleService;
import com.example.springboothibernate.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminUserController {
    private final UserService userService;
    private final RoleService roleService;

    public AdminUserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String getUsers(Model model){
        model.addAttribute("users", userService.getUsers());
        return "users";
    }

    @GetMapping("/add")
    public String addUserPage(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.getAllRoles());
        return "addEditUser";
    }

    @PostMapping("/add")
    public String createUser(@ModelAttribute User user, @RequestParam("roleTypes") Set<Long> roleIds){
        Set<Role> roles = roleService.findByIds(roleIds);
        user.setRoles(roles);
        userService.addUser(user);
        return "redirect:/admin";
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
        model.addAttribute("roles", roleService.getAllRoles());
        return "addEditUser";
    }

    @PostMapping("/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute User user){
        userService.updateUser(id, user);
        return "redirect:/admin";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
