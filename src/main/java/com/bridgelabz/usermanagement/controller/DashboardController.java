package com.bridgelabz.usermanagement.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.usermanagement.model.RegisterUser;
import com.bridgelabz.usermanagement.service.IDashboardService;

/**
 * 
 * @author PanyamRamesh
 * purpose: controller for dash board
 *
 */
@RestController
@RequestMapping("/statistics")
public class DashboardController {
	@Autowired(required = true)
	IDashboardService dashboardService;

	/**
	 * purpose: API for registered users statistics
	 * @return
	 */
	@GetMapping("/userstatistics")
	public ResponseEntity<HashMap<String, Long>> getStatistics() {
		HashMap<String, Long> userCount = dashboardService.getUserStatistics();
		return new ResponseEntity<HashMap<String, Long>>(userCount, HttpStatus.OK);

	}
	/**
	 * purpose: API for latest registered users
	 * @return
	 */
	@GetMapping("/sortuser")
	public ResponseEntity<List<RegisterUser>> getLatestRegisteredUsers()
	{
		
		List<RegisterUser> list=dashboardService.getLatestRegisteredUsers();
		return new ResponseEntity<List<RegisterUser>>(list, HttpStatus.OK);
		
	}

}
