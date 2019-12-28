package com.bridgelabz.usermanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.usermanagement.model.RegisterUser;
import com.bridgelabz.usermanagement.response.Response;
import com.bridgelabz.usermanagement.service.IDashboardService;

@RestController
@RequestMapping("/userstatistics")
public class DashboardController {
	@Autowired
	private IDashboardService dashService;

	/**
	 * 
	 * @param year
	 * @param month
	 * @return response with user registration statistics
	 */
	@GetMapping("/{year}/{month}")
	public ResponseEntity<Response> getYearData(@PathVariable int year, @PathVariable int month) {
		return new ResponseEntity<>(dashService.getUserStat(year, month), HttpStatus.OK);
	}

	/**
	 * purpose: API for latest registered users
	 * 
	 * @return
	 */
	@GetMapping("/sortuser")
	public ResponseEntity<List<RegisterUser>> getLatestRegisteredUsers() {

		List<RegisterUser> list = dashService.getLatestRegisteredUsers();
		return new ResponseEntity<>(list, HttpStatus.OK);

	}
}
