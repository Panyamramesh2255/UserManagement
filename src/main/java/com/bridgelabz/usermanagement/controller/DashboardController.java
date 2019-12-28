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
	 * purpose: API for registered statistics of user
	 * @return
	 */
	@GetMapping("/statistics")
	public ResponseEntity<HashMap<String, Long>> getStatistics() {
		HashMap<String, Long> userCount = dashboardService.getUserStatistics();
		return new ResponseEntity<HashMap<String, Long>>(userCount, HttpStatus.OK);

	}
	
	/**
	 * Sample requests:/userstatistics/2019/05-> particular month data,
	 * /userstatistics/2010/0->particular year data ,/userstatistics/0/0-> Alltime data,
	 * /userstatistics/0/05->current month data
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
