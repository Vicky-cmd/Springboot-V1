package com.infotrends.in.SpringbootV1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.infotrends.in.SpringbootV1.dao.ProductsDataRepository;
import com.infotrends.in.SpringbootV1.data.ProductsData;

@Service
public class ProductsDataServices {

	@Autowired
	private ProductsDataRepository productsRepo;
	

	@Autowired
	private MongoOperations mongoOperations;

	@Autowired
	private SequenceGeneratorService sequenceGenSvc;
	
	public ProductsData save(ProductsData data) {
		data.setPId(sequenceGenSvc.generateSequence("Products"));
		return productsRepo.save(data);
	}
	

	public ProductsData update(ProductsData data) {
		return productsRepo.save(data);
	}
	
	public ProductsData findById(int id) {
		if(productsRepo.findBypId(id).isPresent()) {
			return productsRepo.findBypId(id).get();
		} else {
			return null;
		}
	}
	
	public ProductsData findByIdOld(int id) {
		List<ProductsData> dataList = mongoOperations.find(Query.query(Criteria.where("pId").is(id)), ProductsData.class);
		
		ProductsData data = null;
		
		if(dataList != null && dataList.size()>0) {
			data = dataList.get(0);
		}
		return data;
		
	}
	
	public List<ProductsData> findAll() {
		return productsRepo.findAll();
	}
	
	public void deleteById(int id) {
		if(productsRepo.findById(id).isPresent()) {
			ProductsData data = productsRepo.findById(id).get();
			productsRepo.delete(data);
		}
	}
}
