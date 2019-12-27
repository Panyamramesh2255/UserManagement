package com.bridgelabz.usermanagement.dto;

import java.util.ArrayList;
import java.util.Date;

import lombok.Data;


/**
 * @author PanyamRamesh
 * purpose: DTO class for Registration
 */
@Data
public class RegisterDTO {
	private String emailId;
	private String firstName;
	private String middleName;
	private String lastName;
	private Date dob;
	private String gender;
	private String country;
	private String mobile;
	private String phoneExt;
    private String address;
	private String userName;
	private String password;
	private String confirmPassword;
	private String userRole;
	private String profilePic;
	
	
}
