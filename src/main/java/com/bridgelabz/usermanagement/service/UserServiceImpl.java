package com.bridgelabz.usermanagement.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bridgelabz.usermanagement.dto.DashDTO;
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

	@Autowired(required = true)
	private JavaMailSender javaMailSender;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private TokenUtil util;

	Utility utility = new Utility();

	/**
	 * purpose: login service method
	 */
	public Response validateCredentials(LoginDTO loginDTO) {
		System.out.println("inside validate credentials");
		try {
			RegisterUser user = regRepository.findByEmailId(loginDTO.getEmailId());

			if (user.getEmailId().isEmpty() || user.getPassword().isEmpty())
				throw new LoginException("Please enter both fields!!");

			if (bCryptPasswordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
				System.out.println("inside password matching");
				user.setIsonline(true);
				user.getLoginHistoty().add(new Date());
				regRepository.save(user);
				return new Response(200, null, "Login sucess");
			}

		} catch (Exception e) {
			throw new UnautorizedException("Unauthorized User");

		}
		return new Response(400, null, "LoginFailure");
	}

	/**
	 * purpose: Registration service method
	 */
	public Response registerUser(RegisterDTO regdto) {
		if (regRepository.findByEmailId(regdto.getEmailId()) != null) {
			throw new RegistrationException("EmailId already exist!!");
		}
		if (regRepository.findByMobile(regdto.getMobile()) != null) {
			throw new RegistrationException("Mobile number already exist!!");
		}
		RegisterUser regUser = modelMapper.map(regdto, RegisterUser.class);
		regUser.setPassword(bCryptPasswordEncoder.encode(regdto.getPassword()));
		sendEmail(regdto.getEmailId(), util.getJWTToken(regdto.getEmailId()));
		regUser.setRegisteredDate(LocalDate.now());
		regUser.setIsactive(true);
		givepermissions(regUser);
		regRepository.save(regUser);
		verifyUser(regUser.getEmailId());
		return new Response(200, null, "success");
	}

	/**
	 * purpose: method to send mail
	 */
	@Override
	public Response sendEmail(String email, String token) {
		try {
			SimpleMailMessage mail = new SimpleMailMessage();
			mail.setFrom("panyamramesh2255@gmail.com");
			mail.setTo(email);
			mail.setSubject("Verification Code:");
			mail.setText(token);
			javaMailSender.send(mail);
			System.out.println("mail sended successfullt to " + email);
			return new Response(200, null, Utility.MAILSENT);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(400, null, Utility.MAILNOTSENT);
	}

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

	/**
	 * purpose: method to get all user details
	 */
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

	/**
	 * purpose: method to get profile of a particular user
	 */
	@Override
	public RegisterUser getprofile(String email) {
		RegisterUser registeruser = regRepository.findByEmailId(email);
		if (registeruser == null) {
			throw new LoginException("Invalid email");
		}
		if (!registeruser.isIsonline()) {
			throw new LoginException("your are ofline");
		}

		return registeruser;
	}

	/**
	 * purpose: method to edit profile
	 */
	@Override
	public Response editProfile(RegisterDTO registerDetails) {

		RegisterUser registerUser = regRepository.findByEmailId(registerDetails.getEmailId());
		System.out.println("email  " + registerDetails.getEmailId());
		System.out.println("my details " + registerUser.getAddress());
		System.out.println("above edit profile method");
		utility.test();
		registerUser = utility.editProfile(registerDetails, registerUser);
		regRepository.save(registerUser);
		return new Response(200, null, Utility.PROFILEEDITED);
	}

	/**
	 * purpose: method to get login history of a registered user
	 */
	@Override
	public ArrayList<Date> loginHistory(String email) {
		RegisterUser user = regRepository.findByEmailId(email);
		ArrayList<Date> loginHistory = user.getLoginHistoty();
		return loginHistory;
	}

	/**
	 * purpose: logout method
	 */
	@Override
	public Response logout(String email) {
		RegisterUser user = regRepository.findByEmailId(email);
		System.out.println("user details " + user);
		if (user.isIsonline()) {
			user.setIsonline(false);
			regRepository.save(user);
			return new Response(200, null, Utility.LOGOUTSUCCESS);
		}
		return new Response(400, null, Utility.LOGOUTFAILURE);
	}

	public void givepermissions(RegisterUser user) {

		if (user.getUserRole().contains("user")) {
			user.getPermissions().put("Dashboard", new HashMap() {
				{
					put("Add", false);
				}
				{
					put("Delete", false);
				}
				{
					put("modify", false);
				}
				{
					put("read", false);
				}
			});
			user.getPermissions().put("Settings", new HashMap() {
				{
					put("Add", false);
				}
				{
					put("Delete", false);
				}
				{
					put("modify", false);
				}
				{
					put("read", false);
				}
			});
			user.getPermissions().put("User Information", new HashMap() {
				{
					put("Add", false);
				}
				{
					put("Delete", false);
				}
				{
					put("modify", true);
				}
				{
					put("read", false);
				}
			});
			user.getPermissions().put("Web page 1", new HashMap() {
				{
					put("Add", true);
				}
				{
					put("Delete", false);
				}
				{
					put("modify", true);
				}
				{
					put("read", true);
				}
			});
			user.getPermissions().put("Web page 2", new HashMap() {
				{
					put("Add", true);
				}
				{
					put("Delete", false);
				}
				{
					put("modify", true);
				}
				{
					put("read", true);
				}
			});
			user.getPermissions().put("Web page 3", new HashMap() {
				{
					put("Add", true);
				}
				{
					put("Delete", false);
				}
				{
					put("modify", true);
				}
				{
					put("read", true);
				}
			});

		} else {
			user.getPermissions().put("Dashboard", new HashMap() {
				{
					put("Add", true);
				}
				{
					put("Delete", true);
				}
				{
					put("modify", true);
				}
				{
					put("read", true);
				}
			});
			user.getPermissions().put("Settings", new HashMap() {
				{
					put("Add", true);
				}
				{
					put("Delete", true);
				}
				{
					put("modify", true);
				}
				{
					put("read", true);
				}
			});
			user.getPermissions().put("User Information", new HashMap() {
				{
					put("Add", true);
				}
				{
					put("Delete", true);
				}
				{
					put("modify", true);
				}
				{
					put("read", true);
				}
			});
			user.getPermissions().put("Web page 1", new HashMap() {
				{
					put("Add", true);
				}
				{
					put("Delete", true);
				}
				{
					put("modify", true);
				}
				{
					put("read", true);
				}
			});
			user.getPermissions().put("Web page 2", new HashMap() {
				{
					put("Add", true);
				}
				{
					put("Delete", true);
				}
				{
					put("modify", true);
				}
				{
					put("read", true);
				}
			});
			user.getPermissions().put("Web page 3", new HashMap() {
				{
					put("Add", true);
				}
				{
					put("Delete", true);
				}
				{
					put("modify", true);
				}
				{
					put("read", true);
				}
			});

		}

	}

}
