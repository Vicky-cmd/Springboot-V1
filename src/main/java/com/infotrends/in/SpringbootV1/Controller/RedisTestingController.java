package com.infotrends.in.SpringbootV1.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infotrends.in.SpringbootV1.dao.RedisDataRepository;
import com.infotrends.in.SpringbootV1.data.RedisData;

@RestController
@RequestMapping("/redis")
public class RedisTestingController {

	@GetMapping(value = "")
	public String testController() {
		
		return "Status - 200 - OK";
	}
		
	@Autowired 
	RedisDataRepository redisDataRepository;
	
	/**
	 * Operations On Strings*/
	
	
	@PostMapping("/addDataAsString")
	public String insertDataToRedisCacheAsString(@RequestBody RedisData input) {
		
		ObjectMapper mapper = new ObjectMapper();
		
		String data;
		try {
			data = mapper.writeValueAsString(input);
			redisDataRepository.saveValueAsString(input.getUsername() ,data);
			redisDataRepository.expireString(input.getUsername());
			return "{\"status\": \"200\"}";
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return "{e}";
		}
	}

	@GetMapping("/findDataAsString/{id}")
	public RedisData FindDataAsString(@PathVariable("id") String username) {
		
		ObjectMapper mapper = new ObjectMapper();
		RedisData data= null;
		try {
			String obj = redisDataRepository.findStringbyId(username);
			if(obj!=null && !obj.trim().isEmpty())
				data = mapper.readValue(obj, RedisData.class);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
				
		return data;
	}
	
	/**
	 * Operations On List*/
	
	@PostMapping("/operateOnList/{operation}/{listName}")
	public Object operateOnList(@PathVariable("operation") @NotNull String operation, @PathVariable("listName") String listName, @RequestBody RedisData data) {

		if(operation.equalsIgnoreCase("lpush")) {
			redisDataRepository.leftPushToList(listName, data);
			return redisDataRepository.getListMembers(listName);
		} else if(operation.equalsIgnoreCase("rpush")) {
			redisDataRepository.rightPushToList(listName, data);
			return redisDataRepository.getListMembers(listName);
		} else if(operation.equalsIgnoreCase("lpop")) {
			redisDataRepository.leftPopToList(listName);
			return redisDataRepository.getListMembers(listName);
		} else if(operation.equalsIgnoreCase("rpop")) {
			redisDataRepository.rightPopToList(listName);
			return redisDataRepository.getListMembers(listName);
		} else if(operation.equalsIgnoreCase("lsize")) {
			return redisDataRepository.getListCount(listName);
		} else if(operation.equalsIgnoreCase("emptylist")) {
			redisDataRepository.deleteList(listName);
			return redisDataRepository.getListMembers(listName);
		}     
		
		return "{\"error\": \"OOPs\"}";
	}
	
	
	/**
	 * Operations On Sets*/
	
	@PostMapping("/operateOnSet/{operation}/{setName}")
	public Object operateOnSet(@PathVariable("operation") @NotNull String operation, @PathVariable("setName") String setName, @RequestBody RedisData data) {

		
		if(operation.equalsIgnoreCase("addSet")) {
			redisDataRepository.AddToSet(setName, data);
			return redisDataRepository.getSetMembers(setName);
		} else if(operation.equalsIgnoreCase("popSet")) {
			redisDataRepository.PopFromSet(setName);
			return redisDataRepository.getSetMembers(setName);
		} else if(operation.equalsIgnoreCase("isPresentInSet")) {
			return redisDataRepository.isPresentInSet(setName, data);
		} else if(operation.equalsIgnoreCase("memCountSet")) {
			return redisDataRepository.getSetMembersCount(setName);
		}
		
		return "{\"error\": \"OOPs\"}";
	}
	
	
	/**
	 * Operations On Sorted Sets*/
	
	@PostMapping("/operateOnZSet/{operation}/{setName}/{score}")
	public Object operateOnZSet(@PathVariable("operation") @NotNull String operation, @PathVariable("setName") String setName, @PathVariable("score") Double score, @RequestBody RedisData data) {

		
		if(operation.equalsIgnoreCase("addSet")) {
			redisDataRepository.AddToZSet(setName, data, score);
			return redisDataRepository.getZSetMembers(setName);
		} else if(operation.equalsIgnoreCase("memCountSet")) {
			return redisDataRepository.getZSetMembersCount(setName);
		} else if(operation.equalsIgnoreCase("incScoreInSet")) {
			return redisDataRepository.IncrementScoreInZSet(setName, data, score);
		} else if(operation.equalsIgnoreCase("getMemInSet")) {
			return redisDataRepository.getZSetMembers(setName);
		}
		
		return "{\"error\": \"OOPs\"}";
	}
	
	
	/**
	 * Operations On Hash Functions*/
	
	@PostMapping("/addDataToRedisCache/{hName}")
	public Map<String, RedisData> insertDataToRedisCache(@PathVariable("hName") String hName, @RequestBody RedisData data) {
		redisDataRepository.save(hName, data);
		return redisDataRepository.findAll(hName);
	}


	@PostMapping("/updateDataToRedisCache/{hName}")
	public Map<String, RedisData> updateDataToRedisCache(@PathVariable("hName") String hName, @RequestBody RedisData data) {
		redisDataRepository.update(hName, data);
		return redisDataRepository.findAll(hName);
	}


	@GetMapping("/deleteDataToRedisCache/{hName}/{userName}")
	public Map<String, RedisData> deleteDataToRedisCache(@PathVariable("hName") String hName, @PathVariable("userName") String Username) {
		redisDataRepository.delete(hName, Username);
		return redisDataRepository.findAll(hName);
	}


	@GetMapping("/findDataToRedisCache/{hName}/{id}")
	public RedisData FindDataToRedisCache(@PathVariable("hName") String hName, @PathVariable("id") String username) {
		
		return redisDataRepository.findbyId(hName, username);
	}


	@GetMapping("/findAllDataToRedisCache/{hName}")
	public Map<String, RedisData> FindAllDataToRedisCache(@PathVariable("hName") String hName) {
		
		return redisDataRepository.findAll(hName);
	}
	
	
}
