package com.speer.notes.service;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.speer.notes.exception.BadCredentialsException;
import com.speer.notes.exception.UserNotFoundException;
import com.speer.notes.jwt.JwtService;
import com.speer.notes.model.User;
import com.speer.notes.repositories.UserRepository;
import com.speer.notes.utility.Records.JwtResponse;
import com.speer.notes.utility.Records.LoginRequest;
import com.speer.notes.utility.Records.SignupRequest;

@Service
public class UserServiceImpl implements UserService{

	
	private final UserRepository userRepository;
	
	private final JwtService jwtService;
	
	private final BCryptPasswordEncoder passwordEncoder;
	
	public UserServiceImpl(UserRepository userRepository, JwtService jwtService) {
		super();
		this.userRepository = userRepository;
		this.jwtService = jwtService;
		this.passwordEncoder = new BCryptPasswordEncoder();
	}

	@Override
	public void registerUser(SignupRequest request) {
		String encodedPassword = passwordEncoder.encode(request.password());
        User user = new User(request.username(), request.email(), encodedPassword);
        userRepository.save(user);	
	}

	@Override
	public JwtResponse loginRequest(LoginRequest request) {
		
		Optional<User> userResponse = userRepository.findByUsername(request.username());
		if (userResponse.isPresent()) {
            User user = userResponse.get();
            if (passwordEncoder.matches(request.password(), user.getPassword())) {
                String token = jwtService.generateToken(user.getUsername());
                return  new JwtResponse(token);
            }else
            	throw new BadCredentialsException("Invalid Username or Password");
        }
		throw new UserNotFoundException("User Not Found");
	}

}
