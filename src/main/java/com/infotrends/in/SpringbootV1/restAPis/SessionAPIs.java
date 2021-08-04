package com.infotrends.in.SpringbootV1.restAPis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infotrends.in.SpringbootV1.Connections.ConnectionInterface;
import com.infotrends.in.SpringbootV1.bussiness.MqOrchestrator;
import com.infotrends.in.SpringbootV1.bussiness.Orchestrator;
import com.infotrends.in.SpringbootV1.data.Users;
import com.infotrends.in.SpringbootV1.model.UsersAuthModel;
import com.infotrends.in.SpringbootV1.service.UsersService;

@RestController
@RequestMapping("/api/v1/session")
public class SessionAPIs {
	
	
	@Autowired
	Orchestrator businessOrc;
	
	@PostMapping(value = "/getSessionData", produces = MediaType.APPLICATION_JSON_VALUE)
	public String loginUser(HttpServletRequest request, HttpServletResponse response) {
		JSONObject resp = new JSONObject();
		HttpSession session =  request.getSession();
		if(session!=null) {
			if(session.getAttribute("userId")!=null && !session.getAttribute("userId").toString().isEmpty()) {
				int userId = Integer.parseInt(session.getAttribute("userId").toString());
				String username = session.getAttribute("username").toString();
				String fullname = session.getAttribute("fullname").toString();
				String isAdmin = session.getAttribute("isAdmin")!=null?session.getAttribute("isAdmin").toString():"";
				resp.put("userId", userId);
				resp.put("username", username);
				resp.put("fullname", fullname);
				resp.put("isAdmin", isAdmin);
			}
		}
		System.out.println(resp);
		return resp.toString();
	}	

}
