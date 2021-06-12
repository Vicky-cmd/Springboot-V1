package com.infotrends.in.SpringbootV1.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

	@RequestMapping("/")  
	public String welcomeMsg()   
	{  
	return "<h1>Welcome User!</h1>";  
	}  
	
	@RequestMapping("/Home")  
	public String displayHomePage()   
	{  
	return "<h1>Welcome Home!</h1>";  
	}
	
	@GetMapping("/userInfo/{username}")  
	public String displayUserInfo(@PathVariable String username)   
	{  
	return "<h1>Hi " + username + "!</h1>";  
	}
}
