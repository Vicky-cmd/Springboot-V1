package com.infotrends.in.SpringbootV1.Controller;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.infotrends.in.SpringbootV1.bussiness.Orchestrator;
import com.infotrends.in.SpringbootV1.dao.RedisDataRepository;
import com.infotrends.in.SpringbootV1.data.AppConfigs;
import com.infotrends.in.SpringbootV1.data.RedisData;
import com.infotrends.in.SpringbootV1.data.Users;
import com.infotrends.in.SpringbootV1.service.AppConfigsService;
import com.infotrends.in.SpringbootV1.service.CommentsServices;
import com.infotrends.in.SpringbootV1.service.UsersService;

@RestController
public class AppRestController {

	@Autowired
	UsersService usersService;
	@Autowired
	CommentsServices commentsService;
	@Autowired
	Orchestrator orchestrator;
	@Autowired
	AppConfigsService appConfigService;
	@Autowired 
	RedisDataRepository redisDataRepository;
	
	@GetMapping("/userInfo/{username}")  
	public String displayUserInfo(@PathVariable String username)   
	{  
		return "<h1>Hi " + username + "!</h1>";  
	}
	

	
	@RequestMapping("/testConnection")
	public String testConnection(){
		
		return "Status - OK!";
	}
	

	@PostMapping("/saveComments")
	public String saveComments(HttpServletRequest request, HttpServletResponse response, 
			@RequestBody String jsonStr) {
//		commentsService
		JSONObject jsonRequest = new JSONObject(jsonStr);
		JSONObject responseObj = orchestrator.saveCommentsData(request, response, jsonRequest);
		if(responseObj.has("status") && responseObj.get("status").toString().equalsIgnoreCase("200")) {
			response.setStatus(HttpStatus.SC_OK);
		}
		return responseObj.toString();
	}
	
	@PostMapping("/fatchLatestUpdates")
	public String loadUserInfo(HttpServletRequest request, HttpServletResponse response, 
			@RequestBody String jsonStr) {
		JSONObject jsonRequest = new JSONObject(jsonStr);
		JSONObject responseObj = orchestrator.fetchLatestUpdates(request, response, jsonRequest);
		if(responseObj.has("status") && responseObj.get("status").toString().equalsIgnoreCase("200")) {
			response.setStatus(HttpStatus.SC_OK);
		}
		return responseObj.toString();
		
	}


	@PostMapping("/uploadImage")
	public String uploadImage(HttpServletRequest request, HttpServletResponse response, 
			@RequestParam("image") MultipartFile multipartFile) {
		
		JSONObject respJSON = new JSONObject();
		
		try {
			
			String usrName = "";
			if(request.getSession()!=null && request.getSession().getAttribute("username")!=null) {
				usrName = request.getSession().getAttribute("username").toString();
			} else {
				respJSON.put("status", "401");
				respJSON.put("errorInfo", "Access Denied! Please Login");
				return respJSON.toString();
			}
			
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			String uploadDir = "";
			uploadDir = "user-photos/" + request.getSession().getAttribute("username").toString();
			
			respJSON = orchestrator.saveFile(uploadDir, fileName, multipartFile, usrName);
			
		}catch (Exception e) {
			respJSON.put("status", "500");
			respJSON.put("errorInfo", "Oops! Something Happened");
			e.printStackTrace();
		}
		return respJSON.toString();
	}
	
	
}
