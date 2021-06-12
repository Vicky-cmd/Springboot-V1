package com.infotrends.in.SpringbootV1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infotrends.in.SpringbootV1.dao.CommentsRepository;
import com.infotrends.in.SpringbootV1.data.Comments;

@Service
public class CommentsServices {

	@Autowired
	private CommentsRepository commRepo;
	
	public Comments findById(int commentsId) {
		return commRepo.findById(commentsId).get();
	}
	
	public Comments saveOrUpdate(Comments comment) {
		return commRepo.save(comment);
	}
	
}
