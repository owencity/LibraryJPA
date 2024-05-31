package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.user.User;
import com.example.demo.domain.user.UserRepository;
import com.example.demo.dto.user.reponse.UserResponse;
import com.example.demo.dto.user.request.UserCreateRequest;
import com.example.demo.dto.user.request.UserUpdateRequest;


public class UserServiceV2 {
	
	private final UserRepository userRepository;
	
	public UserServiceV2(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Transactional
	public void saveUser(UserCreateRequest request) {
		userRepository.save(new User(request.getName(), request.getAge()));
	}
	
	@Transactional(readOnly = true)
	public List<UserResponse> getUsers() {
		return userRepository.findAll().stream()
				.map(UserResponse::new)
				.collect(Collectors.toList());
	}
	
	@Transactional
	public void updateUser(UserUpdateRequest request) {
		User user = userRepository.findById(request.getId())
				.orElseThrow(IllegalArgumentException::new);
		
		user.updateName(request.getName());
	}
	
	@Transactional
	public void deleteUser(String name) {
		User user = userRepository.findByName(name)
		.orElseThrow(IllegalArgumentException::new);
		
		userRepository.delete(user);
	}

}
