package com.infotrends.in.SpringbootV1.Controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.infotrends.in.SpringbootV1.Config.AppConstants;
import com.infotrends.in.SpringbootV1.bussiness.Orchestrator;
import com.infotrends.in.SpringbootV1.data.Articles;
import com.infotrends.in.SpringbootV1.data.Users;
import com.infotrends.in.SpringbootV1.service.ArticlesServices;
import com.infotrends.in.SpringbootV1.service.UsersService;

@Controller
@RequestMapping("/membersdashboard")
public class MemberServicesController {

	@Autowired
	UsersService usersService;
	
	@Autowired
	ArticlesServices articlesService;
	
	@Autowired
	Orchestrator orchestrator;
	
	@GetMapping(value = {"", "/"})
	public ModelAndView displayMemDashboard() {
		ModelAndView mv = new ModelAndView("/membershippage.jsp");
		mv = orchestrator.createModelAndViewForHomeScreen(mv);
		return mv;
	}
	

	
	@GetMapping("/displayArticle")
	public ModelAndView displayArticle(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mv = new ModelAndView("/articlesPage.jsp");
		
		
		String articleIdStr = request.getParameter("articleId");
		if(articleIdStr==null || articleIdStr.isEmpty()) {
			mv = new ModelAndView("forbiddenPage.jsps");
			return mv;
		}
		
		int articleId = Integer.decode(articleIdStr);
		
		Articles article = articlesService.findById(articleId);
		
		if(article!=null && article.getArticleName()!=null) { 
			mv.addObject("article", article);
			return mv;
		} else {
			return new ModelAndView("/pageNotFound.jsp");
		}
	} 
	
	
	@GetMapping("/articles/{page_id}")
	public ModelAndView showArticles(@PathVariable("page_id") int pageNo) {
		
		if(Long.compare(pageNo, 1)==0) {
			return new ModelAndView("forward:/membersdashboard");
		}
		
		ModelAndView mv = new ModelAndView("/searchResults.jsp");
		
		List<Articles> top5Articles = articlesService.findArticlesOrderByDate();
		long articlesCount = articlesService.getArticlesCount();
		List<Articles> latestArticles = articlesService.findArticlesForHome(pageNo, AppConstants.articlesPerPage);

		if(latestArticles!=null && latestArticles.size()>0) {
		
			mv.addObject("articlesLstPg1", latestArticles);
			mv.addObject("page_id", pageNo);
			System.out.println(pageNo);
			
			mv.addObject("articlesCount", articlesCount);
			mv.addObject("articlesLst", top5Articles);
			mv.addObject("action", "displayAllArticles");
			return mv;
		}
		return new ModelAndView("/pageNotFound.jsp");
	}
	
	//Editor Legacy Version - start
	@RequestMapping("/v1")
	public class legacyEditor {
		

		@GetMapping("/createNewArticle")
		public ModelAndView createNewArticle(HttpServletRequest request, HttpServletResponse response) {
			ModelAndView mv = new ModelAndView("/ArticleEditorLegacy.jsp");
			mv.addObject("fullname", request.getSession().getAttribute("fullname"));
			return mv;
		}
		
		@PostMapping("/previewForm")
		public ModelAndView previewArticle(HttpServletRequest request, HttpServletResponse response) {
	
			ModelAndView mv = new ModelAndView("/submitArticle.jsp");
			System.out.println("Called submitArticle");
			String article = "";
			try {
				article = orchestrator.processRequestLegacy(request, response, true);
			} catch (Exception e) {
				article = "Some Error Occured!";
			}
			mv.addObject("article", article);
			return mv;
		}
		
		@PostMapping("/submitForm")
		public String submitArticle(HttpServletRequest request, HttpServletResponse response) {
	
			System.out.println("Called submitArticle");
			Users authorInfo = null;
			String article = "";
			String shortDesc = "";
			try {
				article = orchestrator.processRequestLegacy(request, response, false);
				authorInfo = usersService.getByUsrName(request.getSession().getAttribute("username").toString());
				shortDesc = request.getParameter("short-desc");
				if(shortDesc!=null) {
					shortDesc = shortDesc.replace("<", "&lt;");
					shortDesc = shortDesc.replace(">", "&gt;");
					shortDesc = shortDesc.replace("\n", "<br>");
				}
			} catch (Exception e) {
				article = "Some Error Occured!";
			}
			
			String fullname = request.getSession().getAttribute("fullname")!=null? request.getSession().getAttribute("fullname").toString(): "";
			String articleName = request.getParameter("title");
			Date date = new Date();
			Articles newArticles = new Articles(articleName, article, date, authorInfo);
			newArticles.setPreviewInfo(shortDesc);
			articlesService.saveOrUpdate(newArticles);
			
			return "redirect:/membersdashboard/displayArticle?articleId=" + newArticles.getArticle_id();
		}
		
	}
	//Editor Legacy Version - end -----------------------------------------------------------------------------------------------------------------------
	
	

	@GetMapping("/createNewArticle")
	public ModelAndView createNewArticle(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("/ArticleEditor.jsp");
		mv.addObject("fullname", request.getSession().getAttribute("fullname"));
		return mv;
	}
	
	@PostMapping("/previewForm")
	public ModelAndView previewArticle(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mv = new ModelAndView("/submitArticle.jsp");
		System.out.println("Called submitArticle");
		String article = "";
		try {
			article = orchestrator.processRequest(request, response, true);
		} catch (Exception e) {
			article = "Some Error Occured!";
		}
		mv.addObject("article", article);
		return mv;
	}
	
	@PostMapping("/submitForm") 
	public String submitArticle(HttpServletRequest request, HttpServletResponse response) {

		System.out.println("Called submitArticle");
		Users authorInfo = null;
		String article = "";
		String shortDesc = "";
		try {
			article = orchestrator.processRequest(request, response, false);
			authorInfo = usersService.getByUsrName(request.getSession().getAttribute("username").toString());
			shortDesc = request.getParameter("short-desc");
			if(shortDesc!=null) {
				shortDesc = shortDesc.replace("<", "&lt;");
				shortDesc = shortDesc.replace(">", "&gt;");
				shortDesc = shortDesc.replace("\n", "<br>");
			}
		} catch (Exception e) {
			article = "Some Error Occured!";
		}
		
		String fullname = request.getSession().getAttribute("fullname")!=null? request.getSession().getAttribute("fullname").toString(): "";
		String articleName = request.getParameter("title");
		Date date = new Date();
		Articles newArticles = new Articles(articleName, article, date, authorInfo);
		newArticles.setPreviewInfo(shortDesc);
		articlesService.saveOrUpdate(newArticles);
		
		return "redirect:/membersdashboard/displayArticle?articleId=" + newArticles.getArticle_id();
	}
	
}
