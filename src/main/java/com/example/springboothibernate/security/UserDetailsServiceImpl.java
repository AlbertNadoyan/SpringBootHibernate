package com.example.springboothibernate.security;

import com.example.springboothibernate.model.User;
import com.example.springboothibernate.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userByEmail = userRepository.findByEmailWithRoles(username);
        if(userByEmail.isEmpty()){
            throw new UsernameNotFoundException("Username not found");
        }
        return new CurrentUser(userByEmail.get());
    }
}