package com.infotrends.in.SpringbootV1.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.infotrends.in.SpringbootV1.data.OauthUserData;
import com.infotrends.in.SpringbootV1.data.Users;
import com.infotrends.in.SpringbootV1.service.UsersService;

@Controller
@RequestMapping("/userInfo")
public class UserServicesController {


	@Autowired
	UsersService usersService;
	
	
	@GetMapping(value = {"", "/"}, params = { "userId" })
	public ModelAndView getUserInfo(@RequestParam("userId") int userId) {
		ModelAndView mv = new ModelAndView("/userInfo.jsp");
		Users users = usersService.fingById(userId);
		
		users.setPassword("");
		if(users!=null && users.getIsOauthAccount()!=null && users.getIsOauthAccount().equalsIgnoreCase("Y")) {
			OauthUserData usrData = users.getOauthUsrData();
		} else {
			mv.addObject("usrInitials", String.valueOf(users.getFullname().charAt(0)).toUpperCase() + String.valueOf(users.getFullname().charAt(users.getFullname().length() - 1)).toUpperCase());
		}
		
		mv.addObject("users", users);
		return mv;
	}
}
