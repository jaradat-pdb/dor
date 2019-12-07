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
package org.pdbcorp.sdor.controller;

import org.pdbcorp.sdor.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jaradat-pdb
 *
 */
@Slf4j
@Controller
@RequestMapping("/prds")
public class ProductsController {
	private ProductService productService;

	@Autowired
	public ProductsController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping(value = {"", "/", "/all", "/list"})
	public String getPage() {
		if(log.isTraceEnabled())
			log.trace("Responding to request for products page");
		return "listProducts";
	}

	@GetMapping(value = "/{id}")
	public String getProductById(@PathVariable Integer id, Model model) {
		String page = "";
		if(id == null || id.equals(Integer.valueOf(0))) {
			log.warn("Page requested with id path variable of 0 or null, redirecting to products page");
			page = "redirect:/list";
		} else {
			model.addAttribute("product", productService.getProduct(id));
			page = "product";
		}
		return page;
	}
}
