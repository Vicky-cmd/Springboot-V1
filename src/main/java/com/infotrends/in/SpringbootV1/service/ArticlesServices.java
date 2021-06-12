package com.infotrends.in.SpringbootV1.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.infotrends.in.SpringbootV1.dao.ArticlesRepository;
import com.infotrends.in.SpringbootV1.data.Articles;
import com.infotrends.in.SpringbootV1.data.Users;

@Service
public class ArticlesServices {


	@Autowired
	private ArticlesRepository articlesRepo;
	
	@Autowired
	private UsersService usersService;
	
	
	public Articles findById(int articleId) {
		if(articlesRepo.findById(articleId).isPresent()) {
			return articlesRepo.findById(articleId).get();
		} else {
			return null;
		}
	}
	
	public Articles saveOrUpdate(Articles articles) {
		return articlesRepo.save(articles);
	}
	
	public List<Articles> findArticlesOrderByDate() {
		return articlesRepo.findTop5ByOrderBySubmissionTimeDesc();
	}
	
	public long getArticlesCount() {
		return articlesRepo.count();
	}
	

	public List<Articles> findArticlesForHome(int pageNo, int articlesPerPage) {
		int start =((pageNo) * articlesPerPage) - articlesPerPage;
		int end = articlesPerPage;
		return articlesRepo.findByOrderBySubmissionTimeDesc(PageRequest.of(start/end, end));
	}
	
	public List<Articles> findArticlesStr(String searchStr) {
		List<Articles> result = new ArrayList<Articles>();
		
		HashSet<Articles> tmpresult = new HashSet<Articles>();
		
		HashSet<Articles> artName = articlesRepo.findByArticleNameContaining(searchStr);
		HashSet<Articles> artPreview = articlesRepo.findByPreviewInfoContaining(searchStr);
		HashSet<Articles> artContent = articlesRepo.findByContentContaining(searchStr);
		List<Users> usrsLst = usersService.findUsersByStr(searchStr);
		System.out.println("usrsLst - " + usrsLst.size());
		if(usrsLst==null) {
			usrsLst = new ArrayList<Users>();
		}
		HashSet<Articles> artByUsrName = articlesRepo.findByAuthorInfoIn(usrsLst);
		
		System.out.println("artByUsrName - " + artByUsrName.size());
		
		System.out.println("artName - " + artName.size() + "\nartPreview - " + artPreview.size() + "\nartContent - " + artContent.size() + "\ntmpresult - " + tmpresult.size() + "\n\n\n\n");
		
		if(artName!=null && artName.size()>0) {
			tmpresult.addAll(artName);
		}
		System.out.println("artName - " + artName.size() + "\nartPreview - " + artPreview.size() + "\nartContent - " + artContent.size() + "\ntmpresult - " + tmpresult.size() + "\n\n\n\n");
		if(artPreview!=null && artPreview.size()>0) {
			tmpresult.addAll(artPreview);
		}
		System.out.println("artName - " + artName.size() + "\nartPreview - " + artPreview.size() + "\nartContent - " + artContent.size() + "\ntmpresult - " + tmpresult.size() + "\n\n\n\n");
		if(artContent!=null && artContent.size()>0) {
			tmpresult.addAll(artContent);
		}
		if(artByUsrName!=null && artByUsrName.size()>0) {
			tmpresult.addAll(artByUsrName);
		}
		System.out.println("artName - " + artName.size() + "\nartPreview - " + artPreview.size() + "\nartContent - " + artContent.size() + "\ntmpresult - " + tmpresult.size() + "\n\n\n\n");
		
		result = new ArrayList<Articles>(tmpresult);
		System.out.println(result.size());
		
		return result;
	}
}
