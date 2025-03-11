package com.speer.notes.utility;

public class Records {
	
	// for Signup request
	public record SignupRequest(String username, String email, String password) {}
	
	// used for login request
	public record LoginRequest(String username, String password) {}
	
	//used to JWT response
	public record JwtResponse(String token) {}
	
	//used for error response
	public record ErrorResponse(String message, int status) {}

}
