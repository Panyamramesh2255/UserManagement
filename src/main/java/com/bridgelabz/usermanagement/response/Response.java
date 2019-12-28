package com.bridgelabz.usermanagement.response;

import java.io.Serializable;


import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * author: PanyamRamesh purpose: Class for response
 */
@Data
@AllArgsConstructor
public class Response implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int statusCode;
	private Object data;
	private String message;
}
