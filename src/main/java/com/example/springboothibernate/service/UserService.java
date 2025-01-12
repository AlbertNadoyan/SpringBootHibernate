package com.example.springboothibernate.service;

import com.example.springboothibernate.model.User;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {
    private final RestTemplate restTemplate;
    private String sessionId;

    public UserService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void getAllUser(){
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://94.198.50.185:7081/api/users", String.class);
        sessionId = responseEntity.getHeaders().getFirst(HttpHeaders.SET_COOKIE);
        System.out.println("Session Id " + sessionId);
        System.out.println("Users " + responseEntity.getBody());
    }

    private String addUser(){
        getAllUser();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", sessionId);
        headers.setContentType(MediaType.APPLICATION_JSON);

        User user = new User(3L, "James", "Brown", (byte) 19);
        System.out.println("User data " + user);

        HttpEntity<User> request = new HttpEntity<>(user, headers);
        ResponseEntity<String> response = restTemplate.postForEntity("http://94.198.50.185:7081/api/users", request, String.class);

        System.out.println("Add user " + response.getBody());
        return response.getBody();
    }

    private String updateUser() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", sessionId);
        headers.setContentType(MediaType.APPLICATION_JSON);

        User updateUser = new User(3L, "Emma", "Smith", (byte) 20);
        HttpEntity<User> request = new HttpEntity<>(updateUser, headers);
        ResponseEntity<String> response = restTemplate.exchange("http://94.198.50.185:7081/api/users", HttpMethod.PUT, request, String.class);

        System.out.println("Update user " + response.getBody());
        return response.getBody();
    }

    private String deleteUser() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", sessionId);
        HttpEntity<String> request = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange("http://94.198.50.185:7081/api/users/3", HttpMethod.DELETE, request, String.class);

        System.out.println("Delete user " + response.getBody());
        return response.getBody();
    }

    public void operations() {
        getAllUser();
        String addUser = addUser();
        String updateUser = updateUser();
        String deleteUser = deleteUser();

        String result = addUser + updateUser + deleteUser;
        System.out.println("result " + result);
    }
}
