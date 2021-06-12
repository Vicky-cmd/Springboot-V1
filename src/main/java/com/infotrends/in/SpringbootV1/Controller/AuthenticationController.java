package com.infotrends.in.SpringbootV1.Controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infotrends.in.SpringbootV1.Config.AppConstants;
import com.infotrends.in.SpringbootV1.Connections.ConnectionInterface;
import com.infotrends.in.SpringbootV1.bussiness.MqOrchestrator;
import com.infotrends.in.SpringbootV1.bussiness.Orchestrator;
import com.infotrends.in.SpringbootV1.dao.UsersRepository;
import com.infotrends.in.SpringbootV1.data.OauthUserData;
import com.infotrends.in.SpringbootV1.data.Users;
import com.infotrends.in.SpringbootV1.model.GoogleOauthModel;
import com.infotrends.in.SpringbootV1.model.GoogleTokenModel;
import com.infotrends.in.SpringbootV1.model.UsersAuthModel;
import com.infotrends.in.SpringbootV1.service.UsersService;

@Controller
@RequestMapping("/authenticate")
public class AuthenticationController {

	@Autowired
	UsersService usersService;
	
	@Autowired
	ConnectionInterface connInterface;
	
	@Autowired
	Orchestrator businessOrc;
	
	@Autowired
	MqOrchestrator mqOrc;
	
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	@GetMapping(value = "")
	public String displayloginScreen(Model mv, HttpServletRequest request) {
		
		String referer = request.getParameter("redirect");
		System.out.println(referer);
		mv.addAttribute("usr", new UsersAuthModel());
		return "/login.jsp";
	}

	@PostMapping("/login")
	public String loginUser(HttpServletRequest request, 
			@ModelAttribute("usr") @Validated UsersAuthModel usersAuthModel, 
			Errors errors, Model model) {
		System.out.println("Inside Login Method!");
		if (errors.hasErrors()) {
			return "/login.jsp?register_user=false";
		}
		
		String redirectUrl = request.getParameter("redirect");
		if(usersAuthModel!=null && usersAuthModel.getUsername()!=null && !usersAuthModel.getUsername().isEmpty()
				&& usersAuthModel.getPassword()!=null && !usersAuthModel.getPassword().isEmpty()) {
			Users users = new Users(usersAuthModel);
			Users usersResp = usersService.getByUsrName(usersAuthModel.getUsername());
			System.out.println(usersResp);
			if(usersResp!=null && usersResp.getFullname()!=null) {
				if(usersResp.getPassword()!=null 
						&& encoder.matches(users.getPassword(), usersResp.getPassword())){//&& users.getPassword().equalsIgnoreCase(usersResp.getPassword())) {
					if(usersResp.getIsAccountActive().equalsIgnoreCase("Y")) {
						HttpSession newSession = businessOrc.createSessionDataForLogin(request, usersResp);
						if(redirectUrl!=null && !redirectUrl.isEmpty()) {
							return "redirect:" + redirectUrl;
						} else {
							if(usersResp.getIsAdmin().equalsIgnoreCase("Y")) {
								return "redirect:" + "/adminsdashboard";
							} else {
								return "redirect:" + "/membersdashboard";
							}
						}
					} else {
						errors.rejectValue("email", "Err-Occured", "Account has been Locked.\n Please Contact administrator.");
						return "/login.jsp?register_user=false";
					}
					
				} else {
					if(users.getIsOauthAccount().equalsIgnoreCase("Y")) {
						errors.rejectValue("email", "Err-Occured", "This is Email is linked with a Google Signin Account.<br> Sign in Using Google Sign in");
					} else {
						errors.rejectValue("password", "Err-Occured", "Incorrect Password!");
					}
					return "/login.jsp?register_user=false";
				}
			} else {
				errors.rejectValue("username", "Err-Occured", "Username Does Not Exists!");
				return "/login.jsp?register_user=false";
			}
		} 
		return "redirect:" + "/authenticate";
	}
	
	@PostMapping("/signup")
	public String signupUser(HttpServletRequest request, 
			@ModelAttribute("usr") @Validated  UsersAuthModel usersAuthModel, 
			Errors errors, Model model) {
		System.out.println("Inside Login Method!");
		if (errors.hasErrors()) {
			return "/login.jsp?register_user=true";
		}
		if(usersAuthModel!=null) {
			Users users = new Users(usersAuthModel);
			Users checkExistUsr = usersService.getByUsrName(usersAuthModel.getUsername());
			
			if(checkExistUsr!=null) {
				errors.rejectValue("username", "Err-Occured", 
						"Username Already in Use. Please try a different one!");
				return "/login.jsp?register_user=true";
			}
			
			Users usersResp = usersService.encryptAndSave(users);
			System.out.println(usersResp);
			if(usersResp!=null && usersResp.getFullname()!=null) {
				HttpSession newSession = businessOrc.createSessionDataForLogin(request, usersResp);
				if(usersResp.getIsAdmin().equalsIgnoreCase("Y")) {
					return "redirect:" + "/adminsdashboard";
				} else {
					return "redirect:" + "/membersdashboard";
				}
			}
		}
		return "redirect:" + "/authenticate";
	}
	
	@GetMapping("/logout")
	public ModelAndView logout(HttpServletRequest request) {
		HttpSession newSession = request.getSession();
		newSession.invalidate();
		return  new ModelAndView("redirect:" + "/authenticate");
	}
	
	
	@GetMapping("/google/requestToken/processResponse")
	public String redirectFromGoogleProcessResponse(HttpServletRequest request, HttpServletResponse response) {
		try {
			String code = request.getParameter("code"); 
			System.out.println("Successfully redirected From google!");
			GoogleOauthModel authModel = new GoogleOauthModel(code, AppConstants.client_id, AppConstants.client_secret, AppConstants.base_uri + AppConstants.exec_token_redirect_uri, AppConstants.grant_type);
			ObjectMapper mapper = new ObjectMapper();
			String req;
			req = mapper.writeValueAsString(authModel);
			JSONObject tokenReq = new JSONObject(req);				
			HashMap<String, Object> tokenResp = connInterface.executes("https://www.googleapis.com/oauth2/v4/token", tokenReq, "POST");
			if(tokenResp.get("status")!=null && tokenResp.get("status").toString().equalsIgnoreCase("Success")) {
				GoogleTokenModel tokenModel = mapper.readValue(tokenResp.get("jsonResponse").toString(), GoogleTokenModel.class); 
				String emailInfoUrl = "https://www.googleapis.com/oauth2/v2/userinfo?access_token=" + tokenModel.getAccess_token();
				HashMap<String, Object> userInfo = connInterface.executes(emailInfoUrl, null, "GET");
				if(userInfo!=null && userInfo.get("status")!=null && userInfo.get("status").toString().equalsIgnoreCase("Success")) {
					String emailId = userInfo.get("email").toString();
					String picture = userInfo.get("picture").toString();
					String name = userInfo.get("name").toString();
					String locale = userInfo.get("locale").toString();
					String verified_email = userInfo.get("verified_email").toString();
					Users users = new Users();
					Users checkExistUsr = usersService.getByUsrName(emailId);
					if(checkExistUsr!=null && checkExistUsr.getUsername()!=null && !checkExistUsr.getUsername().equalsIgnoreCase("")) {
						users = checkExistUsr;
					}
					users.setFullname(name);
					users.setUsername(emailId);
					users.setIsOauthAccount("Y");
					OauthUserData usrData = new OauthUserData();
					usrData.setProfilePicUrl(picture);
					usrData.setIsEmailVerified(verified_email);
					usrData.setLocale(locale);
					users.setOauthUsrData(usrData);
					usersService.saveOrUpdate(users);
					HttpSession newSession = businessOrc.createSessionDataForLogin(request, users);
					return "redirect:" + "/";
				} else {
					return "redirect:" + "/authenticate";
				}
			} else {
				return "redirect:" + "/authenticate";
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:" + "/authenticate";
	}	
	
	
	@GetMapping("/verifyAccount/{authToken}")
	public String verifyAccount(@PathVariable("authToken") String authToken , Model mv) {
		
		try {
			JSONObject req = new JSONObject();
			req.put("enc-token", authToken);
			HashMap<String, Object> resp = connInterface.executesForHttp(AppConstants.validateOtpUrl, req, "POST");
			if(resp!=null && resp.get("status")!=null && resp.get("status").toString().equalsIgnoreCase("Success")
					&& resp.get("statusCode")!=null && resp.get("statusCode").toString().equalsIgnoreCase("200")) {
	
				if(resp.get("usr_id")!=null) {
					int userId = Integer.decode(resp.get("usr_id").toString());
					mv.addAttribute("userId", userId);
					mv.addAttribute("authToken", authToken);
					UsersAuthModel userAuth = new UsersAuthModel();
					userAuth.setFullname("");
					Users usr = usersService.fingById(userId);
					if(usr!=null && usr.getUsername()!=null) {
						userAuth.setUsername("usr.getUsername()");
						mv.addAttribute("usr", userAuth);
						return "/resetPwd.jsp";
					}
				} else {
					mv.addAttribute("errorMsg", "Oops! Looks like there is something wrong with the request. Please try again with a new link");
				}
			} else if(resp!=null && resp.get("errorMsg")!=null && !resp.get("errorMsg").toString().isEmpty()) {
				mv.addAttribute("errorMsg", resp.get("errorMsg").toString());
			} else {
				mv.addAttribute("errorMsg", "Oops! Something Happened. Please Try Again");
			}
		} catch (Exception e) {
			mv.addAttribute("errorMsg", "Oops! Something Happened. Please Try Again");
		}

		mv.addAttribute("errorMsg", new Users());
		return "/resetPwd.jsp";
	}
	
	@PostMapping("/updatePassword")
	public String updatePassword(@RequestParam("userId") int userId, @RequestParam("authToken") String authToken, 
			@ModelAttribute("usr") @Validated UsersAuthModel userInp, Errors errors, Model mv) {
		try {
		JSONObject req = new JSONObject();
		req.put("enc-token", authToken);
		HashMap<String, Object> resp = connInterface.executesForHttp(AppConstants.validateOtpUrl, req, "POST");
		if(resp!=null && resp.get("status")!=null && resp.get("status").toString().equalsIgnoreCase("Success")
				&& resp.get("statusCode")!=null && resp.get("statusCode").toString().equalsIgnoreCase("200")) {
		
			Users usr = usersService.fingById(userId);
			usr.setPassword(userInp.getPassword());
			usr.setIsAccountActive("Y");
			usersService.saveOrUpdate(usr);
			mqOrc.sendTokenForInvalidation(authToken);
			return "redirect:/";
		}
		}catch (Exception e) {
			e.printStackTrace();
		}
		mv.addAttribute("usr", userInp);
		return "/resetPwd.jsp";
	}
	
	
	@GetMapping("/forgotPassword")
	public String forgotPassword(Model mv) {
		mv.addAttribute("usr", new Users());
		return "/newPwdReqScreen.jsp";
	}
	
	@PostMapping("/forgotPassword")
	public String generateLinkForNewPwd(@ModelAttribute("usr") UsersAuthModel userInp ,Errors errors, Model mv) {
		
		try {
			String username = userInp.getUsername();
			Users user = usersService.getByUsrName(username);
			if(user!=null && user.getFullname()!=null) {
				mqOrc.sendReqForNewPwd(user);
			} else {
				errors.rejectValue("email", "Err-Occured", "Username not Found in the system!");
				return "/newPwdReqScreen.jsp";
			}
		} catch (Exception e) {
			return "/newPwdReqScreen.jsp";
		}
		
		return "redirect:/";
	}
}
