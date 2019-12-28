package com.bridgelabz.usermanagement.dto;

import java.time.LocalDate;

import lombok.Data;

/**
 * @author PanyamRamesh purpose: DTO class for Registration
 */
@Data
public class RegisterDTO {
	private String emailId;
	private String firstName;
	private String middleName;
	private String lastName;
	private LocalDate dob;
	private String address;
	private String gender;
	private String country;
	private String mobile;
	private String phoneExt;

	private String userName;
	private String password;
	private String confirmPassword;
	private String userRole;
	private String profilePic;

}
