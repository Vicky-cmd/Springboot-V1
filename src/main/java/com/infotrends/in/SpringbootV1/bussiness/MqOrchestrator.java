package com.infotrends.in.SpringbootV1.bussiness;

import javax.transaction.Transactional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.infotrends.in.SpringbootV1.Config.AppConstants;
import com.infotrends.in.SpringbootV1.data.Users;
import com.infotrends.in.SpringbootV1.service.MQServices;
import com.infotrends.in.SpringbootV1.service.UsersService;

@Component
public class MqOrchestrator {

	@Autowired
	UsersService usersService;

	@Autowired
	MQServices mqServices;
	
	@Transactional(rollbackOn = {Exception.class})
	public void saveNewUserData(Users userInp) throws Exception {
		try {
			usersService.saveOrUpdate(userInp);
			JSONObject json = new JSONObject();
			json.put("user_id", userInp.getId());
			json.put("username", userInp.getUsername());
			json.put("fullname", userInp.getFullname());
			json.put("isadmin", userInp.getIsAdmin());
			int status = mqServices.sendDataToMQ(AppConstants.send_mail_toNewRegisteredUsr_QName, json);
			if(!(status == 200)) {
				throw new Exception("Oops! Loks like Something happened!");
			}
		}catch (Exception e) {
			throw new Exception("Oops! Loks like Something happened!");
		}
	}
	
	@Transactional(rollbackOn = {Exception.class})
	public void sendReqForNewPwd(Users userInp) throws Exception {
		try {
			JSONObject json = new JSONObject();
			json.put("user_id", userInp.getId());
			json.put("username", userInp.getUsername());
			json.put("fullname", userInp.getFullname());
			json.put("isadmin", userInp.getIsAdmin());
			int status = mqServices.sendDataToMQ(AppConstants.send_mail_toNewRegisteredUsr_QName, json);
			if(!(status == 200)) {
				throw new Exception("Oops! Loks like Something happened!");
			}
		}catch (Exception e) {
			throw new Exception("Oops! Loks like Something happened!");
		}
	}
	
	@Transactional(rollbackOn = {Exception.class})
	public void sendTokenForInvalidation(String token) throws Exception {
		try {
		JSONObject json = new JSONObject();
		json.put("enc-token", token);
		int status = mqServices.sendDataToMQ(AppConstants.token_deactivation_QName, json);
		if(!(status == 200)) {
			throw new Exception("Oops! Loks like Something happened!");
		}
		}catch (Exception e) {
			throw new Exception("Oops! Loks like Something happened!");
		}
	}
}
