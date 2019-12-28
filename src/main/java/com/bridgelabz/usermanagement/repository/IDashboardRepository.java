package com.bridgelabz.usermanagement.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.usermanagement.model.User;
@Repository
public interface IDashboardRepository extends MongoRepository<User, String> {

}
