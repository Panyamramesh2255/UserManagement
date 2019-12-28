package com.bridgelabz.usermanagement.service;

import java.util.List;
import java.util.Map;

import com.bridgelabz.usermanagement.model.User;
import com.bridgelabz.usermanagement.response.Response;

public interface IDashboardService {

	public Map<String, Double> getGenderPercentage(List<User> userList);

	
	public Map<String, Long> getAgeGroup(List<User> userList);

	
	public Response getUserStat(int year, int month);

	public Map<Integer, Integer> getAlltime();

	
	public Map<Object, Integer> getAllByYear(Integer year);


	public Map<Object, Long> getByMonthAndYear(int month, int year);

	
	public Map<String, Integer> getTopCountries(List<User> userList);
}
