package com.bridgelabz.usermanagement.dto;

import java.time.LocalDate;

import lombok.Data;

/**
 * @author PanyamRamesh purpose: DTO class for Registration
 */
@Data
public class RegisterDTO {
	@NotNull(message = "email field is mandtory" )
	private String emailId;
	@NotNull(message = "first name field is mandtory")
	private String firstName;
	@NotNull(message = "middleName field is mandtory")
	private String middleName;
	@NotNull(message = "lastName field is mandtory")
	private String lastName;
	@NotNull(message = "date of birth field is mandtory")
	private LocalDate dob;
	@NotNull(message = "address field is mandtory")
	private String address;
	@NotNull(message = "gender field is mandtory ")
	private String gender;
	@NotNull(message = "country field is mandtory")
	private String country;
	@NotNull(message = "mobile number field is mandtory")
	private String mobile;
	@NotNull(message = "phoneExt field is mandtory")
	private String phoneExt;
	@NotNull(message = "userName field is mandtory")
	private String userName;
	@NotNull(message = "password field is mandtory")
	private String password;
	@NotNull(message = "confirm password field is mandtory")
	private String confirmPassword;
	@NotNull(message = "userRole field is mandtory")
	private String userRole;
	private String profilePic;

}
