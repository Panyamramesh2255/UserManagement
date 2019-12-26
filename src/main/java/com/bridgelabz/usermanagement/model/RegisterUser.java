package com.bridgelabz.usermanagement.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "User")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUser {
@Id
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
private Boolean verified;
private Date registeredDate;
private Boolean onlineStatus;
private boolean Isactive;
private boolean Isonline;

}
