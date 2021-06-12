package com.infotrends.in.SpringbootV1.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infotrends.in.SpringbootV1.data.ProductsData;
import com.infotrends.in.SpringbootV1.model.ProductsModel;
import com.infotrends.in.SpringbootV1.service.ProductsDataServices;


@RestController
@RequestMapping("api/v1/products")
public class ProductsServicesController {	
	
		
		@Autowired
		private ProductsDataServices productsDataSvc;
		
		@PostMapping("/addNewProduct")
		public List<ProductsData> insertDataToRedisCache(@RequestBody ProductsModel input) {
			ProductsData data = new ProductsData(input);
			productsDataSvc.save(data);
			return productsDataSvc.findAll();
		}
	

		@PostMapping("/updateProduct")
		public List<ProductsData> updateDataToRedisCache(@RequestBody ProductsModel input) {
			ProductsData data = new ProductsData(input);
			productsDataSvc.update(data);
			return productsDataSvc.findAll();
		}
	

		@GetMapping("/deleteProduct/{id}")
		public List<ProductsData> deleteDataToRedisCache(@PathVariable("id") int id) {
			productsDataSvc.deleteById(id);
			return productsDataSvc.findAll();
		}
	

		@GetMapping(value = "/findProductById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
		public ProductsData FindDataToRedisCache(@PathVariable("id") int id) {
			
			return productsDataSvc.findById(id);
		}
	

		@GetMapping(value = "/findAllData", produces = MediaType.APPLICATION_JSON_VALUE)
		public List<ProductsData> FindAllDataToRedisCache() {
			
			return productsDataSvc.findAll();
		}		
		
}
