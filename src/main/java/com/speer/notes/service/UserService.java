package com.speer.notes.service;

import com.speer.notes.utility.Records.JwtResponse;
import com.speer.notes.utility.Records.LoginRequest;
import com.speer.notes.utility.Records.SignupRequest;

public interface UserService {

	public void registerUser(SignupRequest signupRequest);
	
	public JwtResponse loginRequest(LoginRequest loginRequest);
}
