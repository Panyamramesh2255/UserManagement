package com.bridgelabz.usermanagement.dto;

import org.json.JSONArray;
import org.springframework.data.mongodb.core.mapping.DBRef;

public class Permissions {
	@DBRef
	public  JSONArray dashboard;
	@DBRef
	public  JSONArray webpage;
	@DBRef
	public JSONArray settings;
	@DBRef
	public JSONArray userInfo;
	@DBRef
	public JSONArray webPage1;
	@DBRef
	public JSONArray webPage2;
	@DBRef
	public JSONArray webPage3;

}
