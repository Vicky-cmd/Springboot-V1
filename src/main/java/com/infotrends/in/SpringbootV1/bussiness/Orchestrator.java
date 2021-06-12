package com.infotrends.in.SpringbootV1.bussiness;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infotrends.in.SpringbootV1.Config.AppConstants;
import com.infotrends.in.SpringbootV1.data.AppConfigs;
import com.infotrends.in.SpringbootV1.data.Articles;
import com.infotrends.in.SpringbootV1.data.Comments;
import com.infotrends.in.SpringbootV1.data.Users;
import com.infotrends.in.SpringbootV1.service.AppConfigsService;
import com.infotrends.in.SpringbootV1.service.ArticlesServices;
import com.infotrends.in.SpringbootV1.service.CommentsServices;
import com.infotrends.in.SpringbootV1.service.UsersService;

@Component
public class Orchestrator {

	@Autowired
	UsersService usersService;
	@Autowired
	CommentsServices commentsService;
	@Autowired
	ArticlesServices articlesService;
	@Autowired
	AppConfigsService appConfigService;
	
	public HttpSession createSessionDataForLogin(HttpServletRequest request, Users usersResp) {
		HttpSession newSession = request.getSession(true);
		newSession.setMaxInactiveInterval(10000);
		newSession.setAttribute("userId", usersResp.getId());
		newSession.setAttribute("username", usersResp.getUsername());
		newSession.setAttribute("fullname", usersResp.getFullname());
		if(usersResp.getIsAdmin().equalsIgnoreCase("Y")) {
			newSession.setAttribute("isAdmin", "Y");
		}
		return newSession;
	}
	
	public String processRequest(HttpServletRequest request, HttpServletResponse response, Boolean isPreview) throws MalformedURLException {
		String title = request.getParameter("title");
		String noa = request.getSession().getAttribute("fullname").toString();
		String subdate = request.getParameter("dsub");
		String content = request.getParameter("submissionContent");
		String shortDesc = request.getParameter("short-desc");
		shortDesc = shortDesc.replace("<", "&lt;");
		shortDesc = shortDesc.replace(">", "&gt;");
		shortDesc = shortDesc.replace("\n", "<br>");
		String solution = "";
		if(isPreview) {
			solution += "<h1 align = \"center\" style=\"font-size: 24px;\">" + title + "</h1><h2 style=\"margin: 0px\">" + noa + "</h2><h5 style=\"margin: 5px\">" + subdate + "</h5>";
			solution += "<br>";
			solution += "About the Article (To be Shown During Preview.)<br><h4>" + shortDesc +"</h4><br>";
		}
		content = content.replace("contenteditable=\"true\"", "");
		content = content.replace("contenteditable=true", "");
		
		solution += content;
		return solution;
	}
	
	public String processRequestLegacy(HttpServletRequest request, HttpServletResponse response, Boolean isPreview) throws MalformedURLException {
		String val1;
		String title = request.getParameter("title");
		String noa = request.getSession().getAttribute("fullname").toString();
		String subdate = request.getParameter("dsub");
		String par = request.getParameter("paran");
		String img = request.getParameter("imgn");
		String you = request.getParameter("youn");
		String quo = request.getParameter("quoten");
		String shortDesc = request.getParameter("short-desc");
		shortDesc = shortDesc.replace("<", "&lt;");
		shortDesc = shortDesc.replace(">", "&gt;");
		shortDesc = shortDesc.replace("\n", "<br>");
		int tot = 0;
		if(request.getParameter("tot")!=null && !request.getParameter("tot").isEmpty()) {
			tot = Integer.decode(request.getParameter("tot"));
		}
		int parc = 0;
		if(request.getParameter("parac")!=null && !request.getParameter("parac").isEmpty()) {
			parc = Integer.decode(request.getParameter("parac"));
		}
		int imgc = 0;
		if(request.getParameter("imgc")!=null && !request.getParameter("imgc").isEmpty()) {
			imgc = Integer.decode(request.getParameter("imgc"));
		}
		int youc = 0;
		if(request.getParameter("youc")!=null && !request.getParameter("youc").isEmpty()) {
			youc =Integer.decode(request.getParameter("youc"));
		}
		int quoc = 0;
		if(request.getParameter("quotec")!=null && !request.getParameter("quotec").isEmpty()) {
			quoc = Integer.decode(request.getParameter("quotec"));
		}

		val1 = "Paras = " + par + " <br> Images = " + img + " <br> Youtube = " + you + " <br>Quotes= " + quo + " <br>Total= " + tot; 
		// echo $val1;

			String pleft = "<p class = \"left\">";
			String pright = "<p class = \"right\">";
			String ileft = "<img align=\"left\" class = \"left\" src = \"";
			String iright = "<img align=\"right\" class = \"right\" src = \"";
			you = "<p style=\"clear: both\" align=\"center\"><iframe width=\"420\" height=\"315\" src=\"";
			String quote = "<p class = \"clear\">";

		String solution = " ";

		int p = 0;
		int im = 0;
		int y = 0;
		int q = 0;

		if(isPreview) {
			solution += "<h1 align = \"center\" style=\"font-size: 24px;\">" + title + "</h1><h2 style=\"margin: 0px\">" + noa + "</h2><h5 style=\"margin: 5px\">" + subdate + "</h5>";
			solution += "<br>";
			solution += "About the Article (To be Shown During Preview.)<br><h4>" + shortDesc +"</h4><br>";
		}
		String title1 = "<h1 align = \"center\" style=\"font-size: 24px;\">" + title + "</h1>";
		String noa1 = "<h2 style=\"margin: 0px\">" + noa+ "</h2>";
		String subdate1 = "<h5 style=\"margin: 5px\">" + subdate + "</h5>";


		for (int i = 1; i <= tot; i++)
		{
		    if(p < parc)
		    {
		    	String temp1 = "parh" + (p+1);
		    	String temp2 = "";
		        //echo "{$i} {$p} {$parc} {$temp1} ";
		        if(i == Integer.decode(request.getParameter(temp1)))
		        {
		            //echo "Para: {$_POST[$temp1]}";
		            //echo ($par[$p]);
		            temp1 = "Field" + (p+1) + "";
		            //echo "<br> {$temp1}";
		            temp2 = request.getParameter(temp1);
		            temp2 = temp2.replace("<", "&lt;");
		            temp2 = temp2.replace(">", "&gt;");
		            temp2 = temp2.replace("\n", "<br>");
		            //echo "<br> {$temp2}";
		            if(p % 2 == 0)
		            {
		                solution = solution + pleft + temp2 + "</p>";            
		            }
		            else
		            {
		                solution = solution + pright + temp2 + "</p>";
		            }
		            //echo "{$solution}";
		            p++;
		            //echo "<br>";
		            continue;
		        }
		        //echo "<br>";
		    }
		    if (im < imgc)
		    {
		    	String temp1 = "imgh" + (im+1);
		    	String temp2 = "";
		        //echo "{$i} {$im} {$imgc} {$temp1} ";
		        if(i == Integer.decode(request.getParameter(temp1)))
		        {
		            //echo "Img: {$_POST[$temp1]}";
		            //echo ($par[$im]);
		            temp1 = "Img" + (im+1) + "";
		            //echo "<br> {$temp1}";
		            temp2 = request.getParameter(temp1);
		            temp2 = temp2.replace("<", "&lt;");
		            temp2 = temp2.replace(">", "&gt;");
		            //echo "<br> {$temp2}";
		            String end = "\"" + "name = \"" + temp1 + "\"" + "id = \"" + temp1 + "\"" + ">";
		            //$label = $_POST["Label for {$temp2}"];
		            if(im % 2 == 0)
		            {
		                solution = solution + ileft + temp2 + end; //"'>";
		                //$solution = "{$solution} <label for='{$temp1}'>{$label}</label>";             
		            }
		            else
		            {
		                solution = solution + iright + temp2 + end; //"'>";
		                //$solution = "{$solution} <label for='{$temp1}'>{$label}</label>";
		            }
		            //echo "{$solution}";
		            im++;
		            //echo ($img[$im]);
		            //echo "<br>";
		            //echo "<br>";
		            continue;
		        } 
		        //echo "<br>";   
		    }
		    if (y < youc)
		    {
		        String temp1 = "youh" + (y+1);
		    	String temp2 = "";
		        //echo "{$i} {$y} {$youc} {$temp1} ";
		        if(i == Integer.decode(request.getParameter(temp1)))
		        {
		            //echo "Youtube: {$_POST[$temp1]}";
		            //echo ($par[$y]);
		            temp1 = "You_Link" + (y+1) + "";
		            //echo "<br> {$temp1}";
		            temp2 = request.getParameter(temp1);
		            temp2 = temp2.replace("<", "&lt;");
		            temp2 = temp2.replace(">", "&gt;");
		            //echo "<br> {$temp2}";
		            URL vidUrl = new URL(temp2); 
		            String[] hosts = vidUrl.getHost().toString().split("\\.");
		            List checkFb = Arrays.asList("facebook", "fb");
		            boolean isFb = false;
		            for(String host:hosts) {
		            	if(checkFb.contains(host.toLowerCase())) {
		            		isFb = true;
		            		break;
		            	}
		            }
		            //solution += "<h1>" + String.valueOf(isFb) + "</h1>";
		            if(isFb){
		           		solution = solution + "<div class=\"align-items-center\" style=\"width: fit-content; margin: auto;\"><div class=\"fb-video\" data-href=\""+ temp2 + 
		            				"\" data-width=\"500\" data-show-text=\"false\"><blockquote cite=\""+ temp2 + 
		            				"/\" class=\"fb-xfbml-parse-ignore\"><a href=\""+ temp2 + 
		            				"/\">Fb Link</a><p>Shared From Facebook...</p>" + 
		            				"Posted by <a href=\"https://www.facebook.com/facebookapp/\">Facebook App</a> on Friday, 5 December 2014</blockquote></div></div>";
		            } else {
		                solution = solution + you + temp2 + "\"></iframe></p>";
		            }
		            //echo "{$solution}";
		            y++;
		            //echo ($you[$y]);
		            //echo "<br>";
		            //echo "<br>";
		            continue;
		        }
		        //echo "<br>";
		    }    
		    if (q < quoc)
		    {
		        String temp1 = "quoh" + (q+1);
		    	String temp2 = "";
		        //echo "{$i} {$q} {$quoc} {$temp1} ";
		        if(i == Integer.decode(request.getParameter(temp1)))
		        {
		            //echo "Quote: {$_POST[$temp1]}";
		            //echo ($par[$q]);
		            temp1 = "Quote" + (q+1) + "";
		            //echo "<br> {$temp1}";
		            temp2 = request.getParameter(temp1);
		            temp2 = temp2.replace("<", "&lt;");
		            temp2 = temp2.replace(">", "&gt;");
		            //echo "<br> {$temp2}";
		                solution = solution + quote + temp2 + "</p>";            
		            //echo "{$solution}";
		            q++;
		            //echo ($quo[$q]);
		            //echo "<br>";
		            //echo "<br>";
		            continue;
		        }    
		        //echo "<br>";
		    }
		}

		solution += "<br>";
		
		
		return solution;
	}
	
	
	public JSONObject saveCommentsData(HttpServletRequest request, HttpServletResponse response,
			JSONObject jsonRequest) {
		JSONObject jsonResponse = new JSONObject();
		
		try {
			
			String content = jsonRequest.getString("content");
			int articleId = jsonRequest.getInt("article_id");
			String parentCommentIdStr = null;
			if(jsonRequest.has("parentCommentId") && !jsonRequest.get("parentCommentId").toString().isEmpty()) {
				parentCommentIdStr = jsonRequest.getString("parentCommentId");
			}
			String username = request.getSession().getAttribute("username").toString();
			
			Articles articles = articlesService.findById(articleId);
			Comments parentComment = null;
			
			if(parentCommentIdStr!=null && !parentCommentIdStr.isEmpty()) {
				int parentCommentId = Integer.decode(parentCommentIdStr);
				parentComment = commentsService.findById(parentCommentId);
			}
			Users autherInfo = usersService.getByUsrName(username);
			
			Comments newComments = new Comments(autherInfo, content, articles, parentComment);
			commentsService.saveOrUpdate(newComments);
			
			
			jsonResponse.put("status", "200");
			jsonResponse.put("content", content);
			jsonResponse.put("commentsId", newComments.getId());
			jsonResponse.put("parentId", parentCommentIdStr);
			jsonResponse.put("artileId", articleId);
			jsonResponse.put("userId", autherInfo.getId());
			jsonResponse.put("fullname", autherInfo.getFullname());
			
		} catch (Exception e) {
			jsonResponse.put("status", "502");
			jsonResponse.put("errorInfo", "Sorry! Something Went wrong.");
		}
		
		return jsonResponse;
	}
	
	public JSONObject fetchLatestUpdates(HttpServletRequest request, HttpServletResponse response,
			JSONObject jsonRequest) {
		JSONObject jsonResponse = new JSONObject();
	
		try {
			
			List<Articles> top3Articles = articlesService.findArticlesOrderByDate();
		
			ObjectMapper mapper = new ObjectMapper();
//			List<JSONObject> articlesLst = new ArrayList<JSONObject>();
//			for(Articles article:top3Articles) {
//				String objStr = mapper.writeValueAsString(article);
//				JSONObject subObject = new JSONObject(objStr);
//				articlesLst.add(subObject);
//				
//			}
			jsonResponse.put("articlesLst", top3Articles);
			jsonResponse.put("status", "200");
			 System.out.println(jsonResponse.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return jsonResponse;
	}
	
	public ModelAndView createModelAndViewForHomeScreen(ModelAndView mv) {
		

		List<Articles> top5Articles = articlesService.findArticlesOrderByDate();
		long articlesCount = articlesService.getArticlesCount();
		List<Articles> latestArticles = new ArrayList<Articles>();
		if(Long.compare(articlesCount, 0)>0) {
			latestArticles = articlesService.findArticlesForHome(1, AppConstants.articlesPerPage);
		}
		mv.addObject("page_id", 1);
		mv.addObject("articlesLstPg1", latestArticles);
		mv.addObject("action", "displayAllArticles");
		mv.addObject("articlesCount", articlesCount);
		mv.addObject("articlesLst", top5Articles);
		
		return mv;
	}
	
	public Page<Articles> toPage(List<Articles> list, int pagesize, int pageNo) {

	    int totalpages = list.size() / pagesize;
        PageRequest pageable = PageRequest.of(pageNo, pagesize);

        int max = pageNo>=totalpages? list.size():pagesize*(pageNo+1);
        int min = pageNo >totalpages? max:pagesize*pageNo;

//	        System.out.println("totalpages{} pagesize {} pageNo {}   list size {} min {}   max {} ...........", totalpages,pagesize, pageNo, list.size(),
//	                min, max);
        Page<Articles> pageResponse = new PageImpl<Articles>(list.subList(min, max), pageable,
                list.size());
        return pageResponse;
    }
	
	@Transactional(rollbackOn = {Exception.class})
	public JSONObject saveFile(String uploadDir, String fileName,
            MultipartFile multipartFile, String usrName) throws Exception {
		JSONObject respJSON = new JSONObject();
		
		AppConfigs appConfig = appConfigService.fingById("file_id");
		if(appConfig==null || appConfig.getApp_id()==null) {
			appConfig = new AppConfigs();
			appConfig.setApp_id("file_id");
			appConfig.setIntValue(0);
			appConfig.setStrValue("");
			appConfig = appConfigService.saveOrUpdate(appConfig);
		}
		fileName = fileName.substring(0, fileName.lastIndexOf(".")) + "_" + (appConfig.getIntValue() + 1) + fileName.substring(fileName.lastIndexOf("."), fileName.length());
		appConfig.setIntValue(appConfig.getIntValue() + 1);
		appConfigService.saveOrUpdate(appConfig);
		
		Path uploadPath = Paths.get("src/main/webapp/" + uploadDir);
		if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
		
		try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } 		
		respJSON.put("status", "200"); 
		respJSON.put("file_path", "/" + uploadDir + "/" + fileName);
		return respJSON;
	}
}
