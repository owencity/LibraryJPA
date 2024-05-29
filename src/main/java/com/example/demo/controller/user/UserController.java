package com.example.demo.controller.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.domain.user.User;
import com.example.demo.dto.user.reponse.UserResponse;
import com.example.demo.dto.user.request.UserCreateRequest;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController

public class UserController {
    private List<User> users = new ArrayList<>();
   
    @PostMapping("/user") 
    public void saveUser(@RequestBody UserCreateRequest request) {
        users.add(new User(request.getName(), request.getAge()));
    }

    @GetMapping("/user")
    public List<UserResponse> getUsers() {
    	List<UserResponse> responses = new ArrayList<>();
        
        for (int i = 0; i < users.size(); i++) {
            // id는 1부터 시작하여 순차적으로 증가하도록 설정합니다.
            responses.add(new UserResponse( i + 1, users.get(i)));
        }
        return responses;
    }
}