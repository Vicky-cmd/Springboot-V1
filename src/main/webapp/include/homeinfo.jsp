<%@page import="com.infotrends.in.SpringbootV1.data.Articles"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="latestUpdates" class="carousel slide" data-ride="carousel">

<style>

.carouselCustomcss {

width: 50%;
margin: auto;
padding: 20px;
align-content: center;
align-items: center;
}

</style>

<%
	if(request.getAttribute("articlesLst")!=null) {
		List<Articles> latestArticles = (List<Articles>) request.getAttribute("articlesLst");
		
		if(latestArticles.size()>0) {
			out.print("<ul class=\"carousel-indicators\">");
			out.print("<li data-target=\"#latestUpdates\" data-slide-to=\"0\" class=\"active\"></li>");
			for(int i=1; i<latestArticles.size(); i++) {
				out.print("<li data-target=\"#latestUpdates\" data-slide-to=\""+ i +"\"></li>");
			}
			out.print("</ul>");
			out.print("<div class=\"carousel-inner\">");
			out.print("<div class=\"carousel-item active\">");
		    out.print("<div class=\"carouselCustomcss\"><div><h1>" + latestArticles.get(0).getArticleName() + "</h1></div>");
		    out.print("<div>By <a href=\"/userInfo?userId="+ latestArticles.get(0).getAuthorInfo().getId() +"\">"+ latestArticles.get(0).getAuthorInfo().getFullname() + "</a></div>");
		    out.print("<br>");
		    out.print("<div><p>" + latestArticles.get(0).getPreviewInfo() + "</p></div>");
		    int taggedCommCount = 0;
		    if(latestArticles.get(0).getTagged_comments()!=null) {
		    	taggedCommCount = latestArticles.get(0).getTagged_comments().size();
		    }
		    out.print("<div><i class=\"fas fa-comment-alt\" ></i><span>  <a href=\"/membersdashboard/displayArticle?articleId="+ latestArticles.get(0).getArticle_id() +"#commentsSection\">"+ taggedCommCount +"</a></span></div>");
		    out.print("<a href=\"/membersdashboard/displayArticle?articleId=" + latestArticles.get(0).getArticle_id() + "\">Click here</a> to go to the main Article.");
			out.print("</div></div>");
			for(int i=1; i<latestArticles.size(); i++) {
				out.print("<div class=\"carousel-item\">");
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
				out.print("</div></div>");
			}
			out.print("</div>");
		} else {
			out.print("<ul class=\"carousel-indicators\">");
			out.print("<li data-target=\"#latestUpdates\" data-slide-to=\"0\" class=\"active\"></li>");
			out.print("</ul>");	
			out.print("<div class=\"carousel-inner\">");
			out.print("<div class=\"carousel-item active\" >");
			out.print("<div class=\"carouselCustomcss\"><img src=\"/assets/images/background-image.jpg\" style=\"width: 100%; height: 20%; margin: auto; \">");
			out.print("<h2 style=\"position: absolute; bottom: 8px; font-variant: small-caps; font-family: sans;\">No Articles Available.</h2></div>");
			out.print("</div>");
			out.print("</div>");
		}
	}
%>
  <br>
  <br>
  <br>
  <!-- Left and right controls -->
  <a class="carousel-control-prev" href="#latestUpdates" data-slide="prev">
    <span class="carousel-control-prev-icon"></span>
  </a>
  <a class="carousel-control-next" href="#latestUpdates" data-slide="next">
    <span class="carousel-control-next-icon"></span>
  </a>
</div>