<%@page import="com.infotrends.in.SpringbootV1.data.Articles"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%
	int artCount = 10;
	int taggedCommCount = 0;
	String action = request.getAttribute("action").toString();
	
	String paginationUrl = "";
	if(action!=null) {
		if(action.equalsIgnoreCase("displayAllArticles")) {
			paginationUrl = "/membersdashboard/articles/";
		} else if(action.equalsIgnoreCase("SearchResults")) {
			String query = request.getParameter("query");
			if(query==null) {
				query = "";
			}
			paginationUrl = "/search?query=" + query + "&pgNo=";
		}
	}

	if(request.getAttribute("articlesLstPg1")!=null) {
		List<Articles> latestArticles = (List<Articles>) request.getAttribute("articlesLstPg1");
		
		for(int i=0; i<latestArticles.size(); i++) {
			//out.print("<div class=\"carousel-item\">");
		    out.print("<div class=\"carouselCustomcss\"><div><h1>" + latestArticles.get(i).getArticleName() + "</h1></div>");
		    out.print("<div>By <a href=\"/userInfo?userId="+ latestArticles.get(i).getAuthorInfo().getId() +"\">"+ latestArticles.get(i).getAuthorInfo().getFullname() + "</a></div>");
		    out.print("<br>");
		    out.print("<div><p>" + latestArticles.get(i).getPreviewInfo() + "</p></div>");
		    taggedCommCount = 0;
		    if(latestArticles.get(i).getTagged_comments()!=null) {
		    	taggedCommCount = latestArticles.get(i).getTagged_comments().size();
		    }
		    out.print("<div><i class=\"fas fa-comment-alt\" ></i><span>  <a href=\"/membersdashboard/displayArticle?articleId="+ latestArticles.get(i).getArticle_id() +"#commentsSection\">"+ taggedCommCount +"</a></span></div>");
		    out.print("<a href=\"/membersdashboard/displayArticle?articleId=" + latestArticles.get(i).getArticle_id() + "\">Click here</a> to go to the main Article.");
			out.print("</div>");
			out.print("<hr>");
		}		
	
	}

	String nextBtn = "";
	String nextBtnTabIdx = "";
	
	int pageId = Integer.decode(request.getAttribute("page_id").toString());

	out.println("<div style=\"margin: auto; width: fit-content;\"><nav aria-label=\"Navigation\">");
	out.println("<ul class=\"pagination pagination-sm\">");
	if(pageId ==1) {
		out.println("<li class=\"page-item disabled\">");
		out.println("<a class=\"page-link\" href=\"#\" tabindex=\"-1\">Previous</a>");
	} else {
		out.println("<li class=\"page-item\">");
		out.println("<a class=\"page-link\" href=\"" + paginationUrl + (pageId - 1) +"\">Previous</a>");
	}
	out.println("</li>");
	
	
	if(pageId ==1) {
		out.println("<li class=\"page-item active\"><a class=\"page-link\" href=\""+ paginationUrl +"1\"> 1 </a></li>");
	} else {
		out.println("<li class=\"page-item\"><a class=\"page-link\" href=\""+ paginationUrl +"1\"> 1 </a></li>");
	}
	
	long maxPages = 0;
	
	if(request.getAttribute("articlesCount")!=null && !request.getAttribute("articlesCount").toString().isEmpty()) {
		
		if(Long.compare(pageId,5)>0) {
			out.println("<li class=\"page-item disabled\"><a class=\"page-link\"> ... </a></li>");
		}
		
		maxPages = Long.decode(request.getAttribute("articlesCount").toString()) / artCount + ((Long.decode(request.getAttribute("articlesCount").toString()) % artCount)!=0?1:0);

		for(long i=pageId - 3; i<=(pageId-1); i++) {
			if(Long.compare(i,1)<=0) {
				continue;
			}
			out.println("<li class=\"page-item d-sm-none d-md-block\"><a class=\"page-link\" href=\"" + paginationUrl + (i) +"\">"+ (i) +"</a></li>");
		}
		
		if(pageId!=1) {
			out.println("<li class=\"page-item active\"><a class=\"page-link\" href=\"" + paginationUrl + pageId +"\">"+ pageId +"</a></li>");
		}
		
		for(long i=pageId + 1; i<=(pageId+3) && i<maxPages; i++) {
			out.println("<li class=\"page-item d-sm-none d-md-block\"><a class=\"page-link\" href=\"" + paginationUrl + (i) +"\">"+ (i) +"</a></li>");
		}
		
		if(pageId<(maxPages - 4)) {
			out.println("<li class=\"page-item disabled\"><a class=\"page-link\"> ... </a></li>");
		}
		
		if(pageId<maxPages) {
			out.println("<li class=\"page-item\"><a class=\"page-link\" href=\"" + paginationUrl + maxPages +"\"> "+ maxPages +" </a></li>");
		}
		
		if(maxPages <= 1 || pageId == maxPages) {
			nextBtn = "disabled";
			nextBtnTabIdx = "tabindex=\"-1\"";
		}
	} else {
		nextBtn = "disabled";
		nextBtnTabIdx = "tabindex=\"-1\"";
	}
	
	out.println("<li class=\"page-item "+ nextBtn +"\">");
	out.println("<a class=\"page-link\" href=\"" + paginationUrl + (pageId + 1) +"\" " + nextBtnTabIdx + ">Next</a>");
	out.println("</li>");
	out.println("</ul>");
	out.println("</nav></div>");
%>