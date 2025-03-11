package com.speer.notes.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.speer.notes.service.UserService;
import com.speer.notes.utility.Records.JwtResponse;
import com.speer.notes.utility.Records.LoginRequest;
import com.speer.notes.utility.Records.SignupRequest;

@RestController
@RequestMapping(path = "/api/auth")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	@PostMapping(path = "/signup")
	@ResponseStatus(code = HttpStatus.CREATED)
	public void registerUser(@RequestBody SignupRequest signupRequest) {
		userService.registerUser(signupRequest);
	}

	@PostMapping(path = "/login")
	@ResponseStatus(code = HttpStatus.OK)
	public JwtResponse loginUser(@RequestBody LoginRequest loginRequest) {
		return userService.loginRequest(loginRequest);
	}
}
