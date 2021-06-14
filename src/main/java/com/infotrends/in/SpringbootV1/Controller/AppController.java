package com.infotrends.in.SpringbootV1.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.infotrends.in.SpringbootV1.bussiness.Orchestrator;
import com.infotrends.in.SpringbootV1.data.Articles;
import com.infotrends.in.SpringbootV1.data.Comments;
import com.infotrends.in.SpringbootV1.data.Users;
import com.infotrends.in.SpringbootV1.service.AppConfigsService;
import com.infotrends.in.SpringbootV1.service.ArticlesServices;
import com.infotrends.in.SpringbootV1.service.CommentsServices;
import com.infotrends.in.SpringbootV1.service.UsersService;

@Controller
public class AppController {

	@Autowired
	UsersService usersService;
	@Autowired
	CommentsServices commentsService;
	@Autowired
	Orchestrator orchestrator;
	@Autowired
	ArticlesServices articlesService;
	@Autowired
	AppConfigsService appConfigService;
	
	
	@RequestMapping("/")  
	public String welcomeMsg(HttpServletRequest request,
			HttpServletResponse response)   
	{
		HttpSession session = request.getSession();
		if(session!=null && session.getAttribute("username")!=null 
				&& !session.getAttribute("username").toString().isEmpty()) {
			if(session.getAttribute("isAdmin")!=null 
					&& session.getAttribute("isAdmin").toString().equalsIgnoreCase("Y")) {
				return "redirect:" + "/adminsdashboard";
			} else {
				return "redirect:" + "/membersdashboard";
			}
		} else {
			return "redirect:/authenticate";
		}

	}  
	
	@GetMapping("/forbidden")
	public String displayForbiddenMsg(HttpServletRequest request, 
			HttpServletResponse response) {
		
		return "forbiddenPage.jsp";
	}
	

	
	@GetMapping("/search")
	public ModelAndView searchDetails(@RequestParam("query") String query, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("/searchResults.jsp");
		
		int pgNo = 1;
		if(request.getParameter("pgNo")!=null && !request.getParameter("pgNo").toString().isEmpty()) {
			pgNo = Integer.decode(request.getParameter("pgNo"));
		}
		
		mv.addObject("page_id", pgNo);
		System.out.println(pgNo);
		
		List<Articles> articlesResults = articlesService.findArticlesStr(query); 
		
		List<Users> usersResults = usersService.findUsersByStr(query);
		
		int page = pgNo;
		if(page>0) {
			page--;
		}
		Page<Articles> pageResponse = orchestrator.toPage(articlesResults, 10, page);
		List<Articles> res = pageResponse.getContent();
		mv.addObject("articlesCount", articlesResults.size());
		mv.addObject("articlesLstPg1", res);
		mv.addObject("action", "SearchResults");
		mv.addObject("usersResultsLst", usersResults);
		
		return mv;
	}
	
	
	
	@GetMapping(value = {"/retailApp", "/retailApp/items", "/retailApp/cart", "/retailApp/viewCart"})  
	public String retailApp()   
	{
		System.out.println("inside Controller => /retailApp**");
		return "/retailApp/index.html";

	}
	

	@GetMapping("/error")
	public String errorMapping(HttpServletRequest request, 
			HttpServletResponse response) {
		
		return "error.jsp";
	}
	
	@GetMapping(value = {"/about-me", "/about-me/jokes", "/retailApp/musicMatch", "/about-me/reminderPro"})  
	public String aboutMeMapper()   
	{
		System.out.println("inside Controller => /about-me**");
		return "/about-me/index.html";

	}
	
}
