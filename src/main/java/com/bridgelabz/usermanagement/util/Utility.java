package com.bridgelabz.usermanagement.util;

import com.bridgelabz.usermanagement.dto.RegisterDTO;
import com.bridgelabz.usermanagement.model.RegisterUser;
import com.bridgelabz.usermanagement.model.User;

/**
 * 
 * @author PanyamRamesh purpose: Utility class for repose messages
 */
public class Utility {
	public static final String RECORD_UPDATED = "Record updated successfully";
	public static final String RECORD_DELETED = "Record deleted successfully";
	public static final String RECORD_NOT_FOUND = "Record not found";
	public static final String NEW_RECORD_CREATED = "New Record created successfully";
	public static final String NEW_RECORD_CREATION_FAILED = "New Record creation failed";
	public static final String RESOURCE_RETURNED = "Fetched resources successfully";
	public static final String OPERATION_FAILED = "Failed to perform operation";
	public static final String RECORD_UPDATION_FAILED = "Failed to update record";
	public static final String EMPTY_FIELD = "Input fields can't be empty";
	public static final String PROFILE_PIC_LOCATION = "/home/admin1/Desktop/Profile_Picture/";
	public static final String PROFILEEDITED = "profile edited successfully";
	public static final String MAILSENT = "main sent successfully";
	public static final String MAILNOTSENT = "main not sent successfully";
	public static final String LOGOUTSUCCESS = "Logout done successfully";
	public static final String LOGOUTFAILURE = "Logout failure";

	public Utility() {

	}

	public void test() {
		System.out.println("for test ");
	}

	public RegisterUser editProfile(RegisterDTO registerUser, RegisterUser user) {

		System.out.println("into edit method");
		user.setFirstName(registerUser.getFirstName());
		user.setMiddleName(registerUser.getMiddleName());
		user.setLastName(registerUser.getLastName());
		System.out.println("traversed above date");
		user.setDob(registerUser.getDob());

		user.setGender(registerUser.getGender());
		user.setCountry(registerUser.getCountry());
		user.setMobile(registerUser.getMobile());
		user.setPhoneExt(registerUser.getPhoneExt());
		user.setAddress(registerUser.getAddress());
		user.setUserName(registerUser.getUserName());
		user.setPassword(registerUser.getPassword());
		user.setConfirmPassword(registerUser.getConfirmPassword());
		user.setUserRole(registerUser.getUserRole());
		user.setProfilePic(registerUser.getProfilePic());
		user.setIsactive(true);

		return user;

	}

}
