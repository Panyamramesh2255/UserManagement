package com.bridgelabz.usermanagement.dto;

import java.time.LocalDate;

import lombok.Data;

/**
 * @author PanyamRamesh purpose: DTO class for Registration
 */
@Data
public class RegisterDTO {
	@NonNull
	private String emailId;
	@NonNull
	private String firstName;
	@NonNull
	private String middleName;
	@NonNull
	private String lastName;
	@NonNull
	private LocalDate dob;
	@NonNull
	private String address;
	@NonNull
	private String gender;
	@NonNull
	private String country;
	@NonNull
	private String mobile;
	@NonNull
	private String phoneExt;
	@NonNull
	private String userName;
	@NonNull
	private String password;
	@NonNull
	private String confirmPassword;
	@NonNull
	private String userRole;
	private String profilePic;

}
