package com.bridgelabz.usermanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.usermanagement.dto.LoginDTO;
import com.bridgelabz.usermanagement.dto.RegisterDTO;
import com.bridgelabz.usermanagement.model.RegisterUser;
import com.bridgelabz.usermanagement.response.Response;
import com.bridgelabz.usermanagement.service.IUserService;

@RestController
@RequestMapping("/user")

public class UserController {

	@Autowired
	private IUserService userService;
	

	/**
	 * Purpose: For user login
	 * 
	 * @param emailId
	 * @param password
	 * @return response with "success" message or LoginException
	 */
	@GetMapping("/login")
	public Response login(@RequestBody LoginDTO loginDto) {
		Response response = userService.validateCredentials(loginDto);
		return new Response(200, response, "success");
	}

	/**
	 * Purpose: For User Registration and to sent token
	 * 
	 * @param regdto
	 * @return response with "success" message or RegistrationException
	 */
	@PostMapping("/register")
	public Response register(@RequestBody RegisterDTO regdto) {
		Response response = userService.registerUser(regdto);
		return new Response(200, response, "success");
	}

	/**
	 * Purpose:To get all registered user
	 * 
	 * @return response with list of registered users or UnautorizedException
	 */
	@GetMapping("/users")
	public ResponseEntity<List<RegisterUser>> getAllUsers() {
		List<RegisterUser> list = userService.getUsers();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

}