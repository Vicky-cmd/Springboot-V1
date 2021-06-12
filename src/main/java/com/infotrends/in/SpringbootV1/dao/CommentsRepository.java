package com.infotrends.in.SpringbootV1.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.infotrends.in.SpringbootV1.data.Comments;
import com.infotrends.in.SpringbootV1.data.Users;

@Repository
public interface CommentsRepository extends CrudRepository<Comments, Integer>{

}
