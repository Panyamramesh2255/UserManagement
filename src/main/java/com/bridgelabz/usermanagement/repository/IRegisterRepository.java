package com.bridgelabz.usermanagement.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bridgelabz.usermanagement.model.RegisterUser;

/**
 * 
 * @author PanyamRamesh
 * purpose: repository interface which extends mongodb
 */
public interface IRegisterRepository extends MongoRepository<RegisterUser, String> {
	public RegisterUser findByMobile(String mobile);

	public RegisterUser findByEmailId(String emailId);

}
