package com.bridgelabz.usermanagement.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.bridgelabz.usermanagement.dto.LoginDTO;
import com.bridgelabz.usermanagement.dto.RegisterDTO;
import com.bridgelabz.usermanagement.model.RegisterUser;
import com.bridgelabz.usermanagement.response.Response;

public interface IUserService {
	public Response validateCredentials(LoginDTO loginDTO);

	public Response registerUser(RegisterDTO regdto);

	public Response sendEmail(String email, String token);

	

	
	
	public Response saveProfilePic(MultipartFile file,String emailId)throws Exception;
	
	public Response deleteProfilePic(String emailId);
	
	public Response updateProfilePic(MultipartFile file,String emailId)throws IOException ;
	
	public List<RegisterUser> getUsers();
	
	public Response verifyUser(String token);
}
