/*
 * Copyright 2019 PDB Corp.
 *
 * Proprietary Software built off of open-source software?
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.pdbcorp.sdor.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.pdbcorp.sdor.domain.model.Product;
import org.pdbcorp.sdor.domain.model.ProductCategory;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jaradat-pdb
 *
 */
@Slf4j
@Service
public class ProductServiceImpl implements ProductService {
	private Map<Integer, Product> productsMap;
	private Map<Integer, ProductCategory> productCategoriesMap;

	@Override
	public Product getProduct(Integer id) {
		if(log.isDebugEnabled())
			log.debug("Retrieving product with id={}", id);
		return productsMap.get(id);
	}

	@Override
	public List<Product> listProducts() {
		if(log.isDebugEnabled())
			log.debug("Retrieving products list");
		return new ArrayList<>(productsMap.values());
	}

	public void loadTestDataMaps() {
		if(log.isTraceEnabled())
			log.trace("Initializing test data maps");
		initProductCategoriesMapTestData();
		initProductsMapTestData();
		if(log.isTraceEnabled())
			log.trace("Successfully initialized test data maps");
	}

	private void initProductCategoriesMapTestData() {
		productCategoriesMap = new HashMap<>();
		ProductCategory productCategory = new ProductCategory();
		productCategory.setId(Integer.valueOf(1));
		productCategory.setCategory("Safety");
		productCategoriesMap.put(productCategory.getId(), productCategory);
		
		productCategory = new ProductCategory();
		productCategory.setId(Integer.valueOf(2));
		productCategory.setCategory("Protection");
		productCategoriesMap.put(productCategory.getId(), productCategory);
		
		productCategory = new ProductCategory();
		productCategory.setId(Integer.valueOf(3));
		productCategory.setCategory("Detection");
		productCategoriesMap.put(productCategory.getId(), productCategory);
		
		productCategory = new ProductCategory();
		productCategory.setId(Integer.valueOf(4));
		productCategory.setCategory("Device");
		productCategoriesMap.put(productCategory.getId(), productCategory);
		
		productCategory = new ProductCategory();
		productCategory.setId(Integer.valueOf(5));
		productCategory.setCategory("Wear");
		productCategoriesMap.put(productCategory.getId(), productCategory);
	}

	private void initProductsMapTestData() {
		productsMap = new HashMap<>();
		List<ProductCategory> productCategories;
		boolean productCategoriesExist = false;
		if(productCategoriesMap != null && !productCategoriesMap.isEmpty())
			productCategoriesExist = true;
		
		Product product = new Product();
		product.setId(Integer.valueOf(1));
		product.setName("SBCA");
		product.setDesc("Self Contained Breathing Apparatus (SCBA) provides users with breathable air for a finite period of time in harmful environments.");
		product.setPrice(BigDecimal.valueOf(99.00));
		product.setImageUrl("/images/sdor_product-sbca.jpg");
		if(productCategoriesExist) {
			productCategories = new ArrayList<>();
			if(productCategoriesMap.containsKey(Integer.valueOf(1)))
				productCategories.add(productCategoriesMap.get(Integer.valueOf(1)));
			if(productCategoriesMap.containsKey(Integer.valueOf(2)))
				productCategories.add(productCategoriesMap.get(Integer.valueOf(2)));
			if(productCategoriesMap.containsKey(Integer.valueOf(3)))
				productCategories.add(productCategoriesMap.get(Integer.valueOf(3)));
			if(productCategoriesMap.containsKey(Integer.valueOf(4)))
				productCategories.add(productCategoriesMap.get(Integer.valueOf(4)));
			
			if(!productCategories.isEmpty())
				product.setProductCategories(productCategories);
		}
		productsMap.put(product.getId(), product);
		
		product = new Product();
		product.setId(Integer.valueOf(2));
		product.setName("Multi-Gas Detector");
		product.setDesc("Provides continuous monitoring and sampling of up to five gases simultaneously. "
				+ "The ideal companion for a variety of applications and the industry backed solution for "
				+ "the reliable and accurate detection of toxic and combustible gases and vapors.");
		product.setPrice(BigDecimal.valueOf(169.00));
		product.setImageUrl("/images/sdor_product-multi-gas-detector.jpg");
		if(productCategoriesExist) {
			productCategories = new ArrayList<>();
			if(productCategoriesMap.containsKey(Integer.valueOf(1)))
				productCategories.add(productCategoriesMap.get(Integer.valueOf(1)));
			if(productCategoriesMap.containsKey(Integer.valueOf(2)))
				productCategories.add(productCategoriesMap.get(Integer.valueOf(2)));
			if(productCategoriesMap.containsKey(Integer.valueOf(4)))
				productCategories.add(productCategoriesMap.get(Integer.valueOf(4)));
			if(productCategoriesMap.containsKey(Integer.valueOf(5)))
				productCategories.add(productCategoriesMap.get(Integer.valueOf(5)));
			
			if(!productCategories.isEmpty())
				product.setProductCategories(productCategories);
		}
		productsMap.put(product.getId(), product);
		
		product = new Product();
		product.setId(Integer.valueOf(3));
		product.setName("Safety Shoes");
		product.setDesc("A wide range of high-quality safety shoes are offered providing users "
				+ "with excellent protection while simultaneously meeting industry mandated standards "
				+ "for safety and protection. Contact us today to learn about the various options currently in stock.");
		product.setPrice(BigDecimal.valueOf(78.00));
		product.setImageUrl("/images/sdor_product-safety-shoes.jpg");
		if(productCategoriesExist) {
			productCategories = new ArrayList<>();
			if(productCategoriesMap.containsKey(Integer.valueOf(1)))
				productCategories.add(productCategoriesMap.get(Integer.valueOf(1)));
			if(productCategoriesMap.containsKey(Integer.valueOf(2)))
				productCategories.add(productCategoriesMap.get(Integer.valueOf(2)));
			if(productCategoriesMap.containsKey(Integer.valueOf(5)))
				productCategories.add(productCategoriesMap.get(Integer.valueOf(5)));
			
			if(!productCategories.isEmpty())
				product.setProductCategories(productCategories);
		}
		productsMap.put(product.getId(), product);
		
		product = new Product();
		product.setId(Integer.valueOf(4));
		product.setName("Safety Mobile Phones");
		product.setDesc("Intrinsically safe mobile phones uniquely designed for use in oil and gas facilities.");
		product.setPrice(BigDecimal.valueOf(50.00));
		product.setImageUrl("/images/sdor_product-safety-mobile-phones.jpg");
		if(productCategoriesExist) {
			productCategories = new ArrayList<>();
			if(productCategoriesMap.containsKey(Integer.valueOf(1)))
				productCategories.add(productCategoriesMap.get(Integer.valueOf(1)));
			if(productCategoriesMap.containsKey(Integer.valueOf(4)))
				productCategories.add(productCategoriesMap.get(Integer.valueOf(4)));
			
			if(!productCategories.isEmpty())
				product.setProductCategories(productCategories);
		}
		productsMap.put(product.getId(), product);
	}
}
