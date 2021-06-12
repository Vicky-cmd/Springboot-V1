package com.infotrends.in.SpringbootV1.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.infotrends.in.SpringbootV1.data.RedisData;



public interface RedisDataRepository {
	
	void saveValueAsString(String id, String data);
	String findStringbyId(String id);
	void expireString(String id);
	
	
	public void leftPushToList(String listName, RedisData data);
	public void rightPushToList(String listName, RedisData data);
	public void leftPopToList(String listName);
	public void rightPopToList(String listName);
	public List<RedisData> getListMembers(String listName); 
	public Long getListCount(String listName); 
	public void deleteList(String listName); 
	
	
	void AddToSet(String setName, RedisData data);
	Set<RedisData> getSetMembers(String setName);
	long getSetMembersCount(String setName);
	Boolean isPresentInSet(String setName, RedisData data);
	void PopFromSet(String setName);
	
	
	void AddToZSet(String setName, RedisData data, double score);
	Set<RedisData> getZSetMembers(String setName);
	long getZSetMembersCount(String setName);
	Double IncrementScoreInZSet(String setName, RedisData data, double score);
	
	
	void save(String hName, RedisData data);
	Map<String, RedisData> findAll(String hName);
	RedisData findbyId(String hName, String username);
	void update(String hName, RedisData data);
	void delete(String hName, String username);
}
