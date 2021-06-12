package com.infotrends.in.SpringbootV1.dao;

import java.util.HashSet;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.infotrends.in.SpringbootV1.data.Articles;
import com.infotrends.in.SpringbootV1.data.Users;

@Repository
public interface UsersRepository extends CrudRepository<Users, Integer>{

	Users findByUsername(String username);
	
	Users findByUsernameAndPassword(String username, String password);
	
	HashSet<Users> findByUsernameContaining(String username); 
	
	HashSet<Users> findByFullnameContaining(String fullname); 
	
}
