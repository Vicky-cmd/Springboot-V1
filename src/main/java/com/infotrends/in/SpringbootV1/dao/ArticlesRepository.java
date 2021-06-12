package com.infotrends.in.SpringbootV1.dao;

import java.util.HashSet;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.infotrends.in.SpringbootV1.data.Articles;
import com.infotrends.in.SpringbootV1.data.Users;

@Repository
public interface ArticlesRepository extends CrudRepository<Articles, Integer> {

	List<Articles> findTop3ByOrderBySubmissionTimeDesc();
	List<Articles> findTop5ByOrderBySubmissionTimeDesc();

    List<Articles> findByOrderBySubmissionTimeDesc(Pageable pageble); 
    
    HashSet<Articles> findByArticleNameContaining(String name); 
    HashSet<Articles> findByPreviewInfoContaining(String previewinfo); 
    HashSet<Articles> findByContentContaining(String content);  
    HashSet<Articles> findByAuthorInfoIn(List<Users> users); 
    
}
