package com.picpay.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.picpay.domain.dtos.UserDTO;
import com.picpay.domain.services.UserService;
import com.picpay.domain.user.User;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService service;

	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody UserDTO user) {
		User newUser = service.createUser(user);
		return new ResponseEntity<>(newUser, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<User>> findAllUsers() {
		List<User> list = service.findAllUsers();
		return ResponseEntity.ok(list);
	}

}
