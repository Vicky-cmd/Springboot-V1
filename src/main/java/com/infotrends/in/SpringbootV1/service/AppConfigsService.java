package com.infotrends.in.SpringbootV1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infotrends.in.SpringbootV1.dao.AppConfigsRepository;
import com.infotrends.in.SpringbootV1.dao.ArticlesRepository;
import com.infotrends.in.SpringbootV1.data.AppConfigs;

@Service
public class AppConfigsService {

	@Autowired
	AppConfigsRepository appConfigsRepo;
	
	public AppConfigs fingById(String id) {
		if(appConfigsRepo.findById(id).isPresent()) {
			return appConfigsRepo.findById(id).get();
		} else {
			return null;
		}
	}
	
	public AppConfigs saveOrUpdate(AppConfigs appConfigs) {
		return appConfigsRepo.save(appConfigs);
	}
}
