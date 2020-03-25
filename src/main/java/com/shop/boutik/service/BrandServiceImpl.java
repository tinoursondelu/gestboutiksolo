package com.shop.boutik.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.boutik.model.Brand;
import com.shop.boutik.repository.BrandRepository;

@Service
public class BrandServiceImpl implements CharacteristicService {
	
	@Autowired
	private BrandRepository brandRepository;
	
	

	@Override
	public Optional<Brand> findById(Long id) {
		return brandRepository.findById(id);
	}

	@Override
	public Optional<Brand> findByLabel(String label) {
		return brandRepository.findByLabel(label);
	}



}
