package com.example.demo.controller.user.user;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.user.reponse.UserResponse;
import com.example.demo.dto.user.request.UserCreateRequest;
import com.example.demo.dto.user.request.UserUpdateRequest;
import com.example.demo.service.UserServiceV2;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
public class UserController {
	
	private final UserServiceV2 userService;
	
	public UserController(UserServiceV2 userService) {
		this.userService = userService;
	}
	
	@PostMapping("/user")
	public void saveUser(@RequestBody UserCreateRequest request)  {
		//TODO: process POST request
		userService.saveUser(request);
		
	}
	
	@GetMapping("/user")
	public List<UserResponse> getUsers() {
		return userService.getUsers();
	}
	
	@PutMapping("/user") 
	public void updateUser (@RequestBody UserUpdateRequest request) {
		userService.updateUser(request);
	}
	
	@DeleteMapping("/user")
	public void deleteUser(@RequestParam String name) {
		userService.deleteUser(name);
	}

}
