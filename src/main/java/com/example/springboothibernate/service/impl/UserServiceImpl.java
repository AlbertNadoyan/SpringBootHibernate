package com.example.springboothibernate.service.impl;

import com.example.springboothibernate.model.User;
import com.example.springboothibernate.repository.UserRepository;
import com.example.springboothibernate.service.UserService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User updateUser(Long id, User user) {
        User userById = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        userById.setName(user.getName());
        userById.setSurname(user.getSurname());
        userById.setEmail(user.getEmail());
        userById.setAge(user.getAge());
        return userRepository.save(userById);
    }

    @Override
    public void deleteUser(Long id) {
        User userById = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(userById);
    }
}
