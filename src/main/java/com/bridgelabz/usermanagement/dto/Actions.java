package com.bridgelabz.usermanagement.dto;

import java.util.HashMap;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import lombok.Data;

@Data
public class Actions {
	@Id
	private String emailId;
    @DBRef 
	public HashMap<String, Boolean> permissions=new HashMap<String, Boolean>();
    public HashMap<String, HashMap<String, Boolean>> actions=new HashMap<String, HashMap<String,Boolean>>();

}
