package com.infotrends.in.SpringbootV1.Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.infotrends.in.SpringbootV1.Config.AppConstants;
import com.infotrends.in.SpringbootV1.bussiness.MqOrchestrator;
import com.infotrends.in.SpringbootV1.bussiness.Orchestrator;
import com.infotrends.in.SpringbootV1.data.Articles;
import com.infotrends.in.SpringbootV1.data.Users;
import com.infotrends.in.SpringbootV1.model.UsersAuthModel;
import com.infotrends.in.SpringbootV1.service.ArticlesServices;
import com.infotrends.in.SpringbootV1.service.MQServices;
import com.infotrends.in.SpringbootV1.service.UsersService;

@Controller
@RequestMapping("/adminsdashboard")
public class AdminServicesController {


	@Autowired
	UsersService usersService;

	@Autowired
	ArticlesServices articlesService;
	
	@Autowired
	Orchestrator orchestrator;

	@Autowired
	MqOrchestrator mqOrc;
	
	@GetMapping(value = {"", "/"})
	public ModelAndView displayAdminsHomePage() {
		ModelAndView mv = new ModelAndView("/adminspage.jsp");
		mv = orchestrator.createModelAndViewForHomeScreen(mv);
		return mv;
	}
	 
	@GetMapping(value = "/displayUsers")
	public String displayUserData(Model model)   
	{  
		List<Users> listOfUsers = new ArrayList<Users>();
		listOfUsers = usersService.getUsersList();
		model.addAttribute("Users", listOfUsers);
		return "/displayUsers.jsp";
	}
	
	@GetMapping("/updateUserData")
	public ModelAndView displayHomePage(@RequestParam("userId") int userId)   
	{  
		ModelAndView mv = new ModelAndView("/adminshome.jsp");
		
		Users user = usersService.fingById(userId);
		
		mv.addObject("usr", user);
		return mv;  
	}
	

	@PostMapping("/updateData")
	public ModelAndView addUsers(Users userInp, Model model) {
		
		usersService.saveOrUpdate(userInp);
		return new ModelAndView("redirect:" + "/adminsdashboard");
	}
	
	@GetMapping("/addNewUser")
	public String addNewUserData(Model model)   
	{  
		Users usr = new Users();
		usr.setIsAccountActive("N");
		model.addAttribute("usr", usr);
		return "/createNewUser.jsp";
	}
	
	
	@PostMapping("/addNewUser")
	public String saveNewUserData(HttpServletRequest request, 
			@ModelAttribute("usr") @Validated Users userInp, 
			Errors errors, Model model) {
		System.out.println("Adding New User!");		
		if (errors.hasErrors()) {
			return "/createNewUser.jsp";
		}
		
		Users usersResp = usersService.getByUsrName(userInp.getUsername());
		System.out.println(usersResp);
		if(!(usersResp!=null && usersResp.getFullname()!=null)) {
			try {
				mqOrc.saveNewUserData(userInp);
			}catch (Exception e) {
				errors.rejectValue("*", "Err-Occured", e.getMessage());
				return "/createNewUser.jsp";
			}
		} else {
			errors.rejectValue("username", "Err-Occured", "Username Already Exists!");
			return "/createNewUser.jsp";
		}
		
//		usersService.saveOrUpdate(userInp);
		List<Users> listOfUsers = new ArrayList<Users>();
		listOfUsers = usersService.getUsersList();
		model.addAttribute("Users", listOfUsers);
		return "/displayUsers.jsp";
	}
	

	@GetMapping("/generateArticlesForTesting/{number}")
	public String generateArticleForTesting(@PathVariable("number") int number, HttpServletRequest request, HttpServletResponse response) {
		
		Users authorInfo = usersService.getByUsrName(request.getSession().getAttribute("username").toString());
		
		for (int i=0; i<number; i++) {
			String fullname = request.getSession().getAttribute("fullname")!=null? request.getSession().getAttribute("fullname").toString(): "";
			String articleName = "Test Article " + i;
			Date date = new Date();
			Articles newArticles = new Articles(articleName, "This is an auto generated Test Article! Seq No - " + i, date, authorInfo);
			newArticles.setPreviewInfo("This is an auto generated Test Article! Seq No - " + i);
			articlesService.saveOrUpdate(newArticles);
		}
		return "redirect:/";
	}

}
