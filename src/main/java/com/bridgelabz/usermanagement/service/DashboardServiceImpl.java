package com.bridgelabz.usermanagement.service;

import java.util.HashMap;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bridgelabz.usermanagement.dto.LoginDTO;
import com.bridgelabz.usermanagement.dto.RegisterDTO;
import com.bridgelabz.usermanagement.exception.LoginException;
import com.bridgelabz.usermanagement.exception.RegistrationException;
import com.bridgelabz.usermanagement.exception.UnautorizedException;
import com.bridgelabz.usermanagement.model.RegisterUser;
import com.bridgelabz.usermanagement.repository.IRegisterRepository;
import com.bridgelabz.usermanagement.response.Response;
@Service
public class DashboardServiceImpl implements IDashboardService {

	@Autowired(required = true)
	IRegisterRepository repository;
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	IRegisterRepository regRepository;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public HashMap<String, Long> getUserStatistics() {
		List<RegisterUser> userList=repository.findAll();
		HashMap<String, Long> map=new HashMap<String, Long>();
		long total=userList.size();
		long active=userList.stream().filter(c->c.isIsactive()).count();
		long inactive=total-active;
		long online=userList.stream().filter(c->c.isIsonline()).count();
		map.put("total ", total);
		map.put("active ", active);
		map.put("inactive ", inactive);
		map.put("online ", online);
		return map; 
	}

	@Override
	public List<RegisterUser> getLatestRegisteredUsers() {
		
		List<RegisterUser> userList=repository.findAll();
		userList.sort((RegisterUser user1, RegisterUser user2)->user1.getRegisteredDate().compareTo(user1.getRegisteredDate()));
		
		return userList;
	}
	
	
	
	
	
	
	
	

}
