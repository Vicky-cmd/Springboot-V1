package com.infotrends.in.SpringbootV1.service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.infotrends.in.SpringbootV1.Config.AppConstants;
import com.infotrends.in.SpringbootV1.dao.UsersRepository;
import com.infotrends.in.SpringbootV1.data.Articles;
import com.infotrends.in.SpringbootV1.data.Users;

@Service
public class UsersService {

	@Autowired
	UsersRepository usersRepository;
	
	public List<Users> getUsersList() {
		List<Users> usersLst = new ArrayList<Users>();
		usersRepository.findAll().forEach(user -> usersLst.add(user));
		return usersLst;
	}
	
	public Users encryptAndSave(Users user)   
	{  
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(AppConstants.bCryptEncoderStrength, new SecureRandom());
		String secPwd = encoder.encode(user.getPassword());
		user.setPassword(secPwd);
		return saveOrUpdate(user);  
	}  
	
	public Users saveOrUpdate(Users user)   
	{  
		return usersRepository.save(user);  
	}  
	
	public void deleteEntry(Users user) {
		usersRepository.delete(user);
	}
	
	public Users getUserByUsrNameAndPwd(Users user) {
		return usersRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
	}
	
	public Users getByUsrName(String userName) {
		return usersRepository.findByUsername(userName);
	}
	
	public Users fingById(int id) {
		return usersRepository.findById(id).get();
	}
	
	public List<Users> findUsersByStr(String searchStr) {
		List<Users> result = new ArrayList<Users>();

		HashSet<Users> tmpresult = new HashSet<Users>();
		
		HashSet<Users> artName = usersRepository.findByUsernameContaining(searchStr);
		HashSet<Users> artPreview = usersRepository.findByFullnameContaining(searchStr);
		
		if(artName!=null && artName.size()>0) {
			tmpresult.addAll(artName);
		}
		if(artPreview!=null && artPreview.size()>0) {
			tmpresult.addAll(artPreview);
		}
		
		result = new ArrayList<Users>(tmpresult);
		System.out.println(result.size());
		
		return result;
	}
	
}
