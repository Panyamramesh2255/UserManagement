package com.bridgelabz.usermanagement.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.bridgelabz.usermanagement.dto.RegisterDTO;
import com.bridgelabz.usermanagement.model.User;
import com.bridgelabz.usermanagement.response.Response;

public interface IUserService {
	public Response validateCredentials(String email, String pass);

	public Response registerUser(RegisterDTO regdto);

	public Response sendEmail(String email, String token);

	public String getJWTToken(String email);

	public Response saveProfilePic(MultipartFile file, String emailId) throws Exception;

	public Response deleteProfilePic(String emailId);

	public Response updateProfilePic(MultipartFile file, String emailId) throws IOException;

	public List<User> getUsers();

	public Response verifyUser(String token);
}
