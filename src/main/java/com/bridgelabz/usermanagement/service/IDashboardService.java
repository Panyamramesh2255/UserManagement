package com.bridgelabz.usermanagement.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bridgelabz.usermanagement.model.RegisterUser;
import com.bridgelabz.usermanagement.response.Response;

public interface IDashboardService {

	public List<RegisterUser> getLatestRegisteredUsers();
	public Map<String, Double> getGenderPercentage(List<RegisterUser> userList);

	public Map<String, Long> getAgeGroup(List<RegisterUser> userList);

	public Response getUserStat(int year, int month);

	public Map<Integer, Integer> getAlltime();

	public Map<Object, Integer> getAllByYear(Integer year);

	public Map<Object, Long> getByMonthAndYear(int month, int year);

	public Map<String, Integer> getTopCountries(List<RegisterUser> userList);
	public HashMap<String, Long> getUserStatistics();

}
