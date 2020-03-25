package com.shop.boutik.controller;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shop.boutik.helper.enumeration.BrandEnum;
import com.shop.boutik.model.Brand;
import com.shop.boutik.service.BrandServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@RestController
@RequestMapping("/test")
public class test {
	
	@Autowired
	private BrandServiceImpl brandService;
	
	

	@GetMapping("/1") /*ok*/
	@ResponseBody
	public void test() {
		
		Optional<Brand> brand = brandService.findById(BrandEnum.Nike.getId());
		
		if (brand.isPresent()) {
			
			if (brand.get().getId() == BrandEnum.Nike.getId()) {
				
				System.out.println("la marque est " + brand.get().getLabel());
				
			}
		}
	}

}
