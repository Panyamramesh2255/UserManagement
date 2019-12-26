package com.bridgelabz.usermanagement.service;

import java.util.HashMap;
import java.util.List;

import com.bridgelabz.usermanagement.model.RegisterUser;

public interface IDashboardService {

	public HashMap<String, Long> getUserStatistics();

	public List<RegisterUser> getLatestRegisteredUsers();

	
	
	
	
}
