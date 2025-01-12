package com.example.springboothibernate;

import com.example.springboothibernate.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringBootHibernateApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(SpringBootHibernateApplication.class, args);
        UserService userService = context.getBean(UserService.class);
        userService.operations();
    }

}
