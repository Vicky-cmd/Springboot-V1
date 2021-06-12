package com.infotrends.in.SpringbootV1.service;

import org.json.JSONObject;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MQServices {

	
	@Autowired
	RabbitTemplate rabbitTemplate;
	
	public int sendDataToMQ(String queue, JSONObject obj) {
		try {
			rabbitTemplate.convertAndSend(queue, obj.toString());
		} catch (Exception e) {
			return 100;
		}
		return 200;
	}
}
