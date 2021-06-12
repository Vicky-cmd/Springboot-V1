package com.infotrends.in.SpringbootV1.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.stereotype.Repository;

import com.infotrends.in.SpringbootV1.data.RedisData;

@EnableRedisRepositories
@Repository
public class RedisDataRepositoryImpl implements RedisDataRepository {

	
	private RedisTemplate<String, RedisData> redisTemplate;
	private ValueOperations valueOperations;
	private HashOperations hashOperations;
	private ListOperations listOperations;
	private SetOperations setOperations;
	private ZSetOperations zSetOperations;

	
	public RedisDataRepositoryImpl(RedisTemplate<String, RedisData> redisTemplate) {
		super();
		this.redisTemplate = redisTemplate;
		hashOperations = redisTemplate.opsForHash();
		valueOperations = redisTemplate.opsForValue();
		listOperations = redisTemplate.opsForList();
		setOperations = redisTemplate.opsForSet();
		zSetOperations = redisTemplate.opsForZSet();
	}

	

	@Override
	public void saveValueAsString(String id, String data) {
		valueOperations.set(id, data);
	}
	
	@Override
	public String findStringbyId(String id) {
		// TODO Auto-generated method stub
		return (String) valueOperations.get(id);
	}

	@Override
	public void expireString(String id) {
		// TODO Auto-generated method stub
		redisTemplate.expire(id, 20, TimeUnit.SECONDS);
	}
	
	
	@Override
	public void save(String hName, RedisData data) {
		// TODO Auto-generated method stub
		hashOperations.put(hName, data.getUsername(), data);
	}
	
	@Override
	public RedisData findbyId(String hName, String username) {
		return (RedisData) hashOperations.get(hName, username);
	}

	@Override
	public Map<String, RedisData> findAll(String hName) {
		// TODO Auto-generated method stub
		return hashOperations.entries(hName);
	}

	@Override
	public void update(String hName, RedisData data) {
		// TODO Auto-generated method stub
		hashOperations.put(hName, data.getUsername(), data);
	}

	@Override
	public void delete(String hName, String username) {
		// TODO Auto-generated method stub
		hashOperations.delete(hName, username);
	}



	@Override
	public void leftPushToList(String listName, RedisData data) {
		// TODO Auto-generated method stub
		listOperations.leftPush(listName, data);
	}

	@Override
	public void rightPushToList(String listName, RedisData data) {
		// TODO Auto-generated method stub
		listOperations.rightPush(listName, data);
	}

	@Override
	public void leftPopToList(String listName) {
		// TODO Auto-generated method stub
		listOperations.leftPop(listName);
	}

	@Override
	public void rightPopToList(String listName) {
		// TODO Auto-generated method stub
		listOperations.rightPop(listName);
	}


	@Override
	public List<RedisData> getListMembers(String listName) {
		// TODO Auto-generated method stub
		return listOperations.range(listName, 0, -1);
	}

	@Override
	public Long getListCount(String listName) {
		// TODO Auto-generated method stub
		return listOperations.size(listName);
	}

	@Override
	public void deleteList(String listName) {
		// TODO Auto-generated method stub
		List<RedisData> lstData = listOperations.range(listName, 0, -1);
		lstData.forEach(data -> {
			System.out.println(data.getUsername());
			listOperations.leftPop(listName);
		});
	}



	@Override
	public void AddToSet(String setName, RedisData data) {
		// TODO Auto-generated method stub
		setOperations.add(setName, data);
	}

	@Override
	public Boolean isPresentInSet(String setName, RedisData data) {
		// TODO Auto-generated method stub
		return setOperations.isMember(setName, data);
	}

	@Override
	public void PopFromSet(String setName) {
		// TODO Auto-generated method stub
		setOperations.pop(setName);
	}

	@Override
	public Set<RedisData> getSetMembers(String setName) {
		// TODO Auto-generated method stub
		return setOperations.members(setName);
	}

	@Override
	public long getSetMembersCount(String setName) {
		// TODO Auto-generated method stub
		return setOperations.size(setName);
	}



	@Override
	public void AddToZSet(String setName, RedisData data, double score) {
		// TODO Auto-generated method stub
		zSetOperations.add(setName, data, score);
	}

	@Override
	public Double IncrementScoreInZSet(String setName, RedisData data, double score) {
		// TODO Auto-generated method stub
		return zSetOperations.incrementScore(setName, data, score);
	}
	
	@Override
	public Set<RedisData> getZSetMembers(String setName) {
		// TODO Auto-generated method stub
		return zSetOperations.range(setName, 0, -1);
	}

	@Override
	public long getZSetMembersCount(String setName) {
		// TODO Auto-generated method stub
		return zSetOperations.zCard(setName);
	}

}
