package com.bridgelabz.usermanagement.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

	/**
	 * purpose: API to get user profile
	 */
	@GetMapping("/profile")
	public ResponseEntity<RegisterUser> getProfile(@RequestHeader String email) {

		RegisterUser registerUser = userService.getprofile(email);
		return new ResponseEntity<RegisterUser>(registerUser, HttpStatus.OK);

	}

	/**
	 * purpose: API to edit user profile
	 * 
	 * @param regsiterDetails
	 * @return
	 */
	@PutMapping("/profile")
	public ResponseEntity<Response> editProfile(@RequestBody RegisterDTO regsiterDetails) {

		Response response = userService.editProfile(regsiterDetails);

		return new ResponseEntity<Response>(response, HttpStatus.OK);

	}

	/**
	 * purpose: API to get login history of a registered user
	 * 
	 * @param email
	 * @return
	 */
	@GetMapping("/loginhistory")
	public ResponseEntity<ArrayList<Date>> loginHistory(@RequestHeader String email) {

		ArrayList<Date> history = userService.loginHistory(email);

		return new ResponseEntity<ArrayList<Date>>(history, HttpStatus.OK);

	}

	/**
	 * purpose: API for logout functionality
	 * @param email
	 * @return
	 */
	@GetMapping("/logout")
	public ResponseEntity<Response> logout(@RequestHeader String email) {

		Response response = userService.logout(email);
		return new ResponseEntity<Response>(response, HttpStatus.OK);

	}
}