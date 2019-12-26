package com.bridgelabz.usermanagement.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bridgelabz.usermanagement.dto.LoginDTO;
import com.bridgelabz.usermanagement.dto.RegisterDTO;
import com.bridgelabz.usermanagement.exception.LoginException;
import com.bridgelabz.usermanagement.exception.RegistrationException;
import com.bridgelabz.usermanagement.exception.UnautorizedException;
import com.bridgelabz.usermanagement.model.RegisterUser;
import com.bridgelabz.usermanagement.repository.IRegisterRepository;
import com.bridgelabz.usermanagement.response.Response;
import com.bridgelabz.usermanagement.util.TokenUtil;
import com.bridgelabz.usermanagement.util.Utility;

@Service

public class UserServiceImpl implements IUserService {

	@Autowired
	private IRegisterRepository regRepository;
	//@Autowired(required = true)
	private JavaMailSender javaMailSender;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private TokenUtil util;

	public Response validateCredentials(LoginDTO loginDTO) {
		RegisterUser user = modelMapper.map(loginDTO, RegisterUser.class);

		if (user.getEmailId().isEmpty() || user.getPassword().isEmpty())
			throw new LoginException("Please enter both fields!!");

		RegisterUser user1 = regRepository.findByEmailId(user.getEmailId());
		user1.setIsonline(true);
		regRepository.save(user1);
		System.out.println("user.getpassword :" + user.getPassword());
		System.out.println("user1.getpassword :" + user1.getPassword());

		System.out.println("bCryptPasswordEncoder.matches(user1.getPassword(), user.getPassword())"
				+ bCryptPasswordEncoder.matches(user1.getPassword(), user.getPassword()));
		if (user1 == null)
			throw new LoginException("Invalid EmailId");
		if (bCryptPasswordEncoder.matches(user1.getPassword(), user.getPassword()))
			return new Response(200, null, "Login sucess");

		throw new UnautorizedException("Unauthorized User");
	}

	public Response registerUser(RegisterDTO regdto) {
		if (regRepository.findByEmailId(regdto.getEmailId()) != null) {
			throw new RegistrationException("EmailId already exist!!");
		}
		if (regRepository.findByMobile(regdto.getMobile()) != null) {
			throw new RegistrationException("Mobile number already exist!!");
		}
		System.out.println("date before mapping "+regdto.getDob());
		RegisterUser regUser = modelMapper.map(regdto, RegisterUser.class);
		System.out.println("date afters mapping "+regUser.getDob());
		regUser.setPassword(bCryptPasswordEncoder.encode(regdto.getPassword()));
		util.sendMail(regdto.getEmailId(), util.encode(regdto.getEmailId()));
		regUser.setRegisteredDate(new Date());
		regRepository.save(regUser);
		verifyUser(regUser.getEmailId());
		return new Response(200, null, "success");
	}

	public Response sendEmail(String email, String token) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("pratikshatamadalge21@gmail.com");
		message.setTo(email);
		message.setSubject("Reset password Varification");
		message.setText("Verification token: " + token);
		javaMailSender.send(message);
		System.out.println("mail>>>> " + message);
		javaMailSender.send(message);
		return new Response(200, null, "Mail sent successfully...");
	}

//	@Override
//	public String getJWTToken(String email) {
//		return TokenUtil.getJWTToken(email);
//	}

	@Override
	public Response saveProfilePic(MultipartFile file, String emailId) throws Exception {
		RegisterUser user = regRepository.findByEmailId(emailId);
		if (user == null)
			throw new UnautorizedException("Unautorized User");

		byte[] bytes = file.getBytes();
		String extension = file.getContentType().replace("image/", "");
		String fileLocation = Utility.PROFILE_PIC_LOCATION + emailId + "." + extension;
		Path path = Paths.get(fileLocation);
		Files.write(path, bytes);

		user.setProfilePic(fileLocation);
		regRepository.save(user);
		return new Response(200, null, Utility.RECORD_UPDATED);
	}

	@Override
	public List<RegisterUser> getUsers() {
		return regRepository.findAll();
	}

	@Override
	public Response deleteProfilePic(String emailId) {
		RegisterUser register = regRepository.findByEmailId(emailId);
		if (register == null)
			throw new UnautorizedException("Unautorized User");

		String fileLocation = register.getProfilePic();
		File file = new File(fileLocation);
		file.delete();
		register.setProfilePic("");
		regRepository.save(register);
		return new Response(200, null, Utility.RECORD_UPDATED);
	}

	public Response updateProfilePic(MultipartFile file, String emailId) throws IOException {

		RegisterUser register = regRepository.findByEmailId(emailId);
		if (register == null)
			throw new UnautorizedException("Unautorized User");

		byte[] bytes = file.getBytes();
		String extension = file.getContentType().replace("image/", "");
		String fileLocation = Utility.PROFILE_PIC_LOCATION + emailId + "." + extension;
		Path path = Paths.get(fileLocation);
		Files.write(path, bytes);

		register.setProfilePic(fileLocation);
		regRepository.save(register);
		return new Response(200, null, Utility.RECORD_UPDATED);

	}

	@Override
	public Response verifyUser(String email) {
		RegisterUser user = regRepository.findByEmailId(email);
		if (user == null)
			throw new UnautorizedException("Unautorized user");
		user.setVerified(true);
		regRepository.save(user);
		return new Response(200, null, Utility.RECORD_UPDATED);
	}

}
