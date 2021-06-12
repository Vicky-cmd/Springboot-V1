package com.infotrends.in.SpringbootV1.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.infotrends.in.SpringbootV1.data.AppConfigs;
import com.infotrends.in.SpringbootV1.data.Articles;

@Repository
public interface AppConfigsRepository extends CrudRepository<AppConfigs, String> {

}
