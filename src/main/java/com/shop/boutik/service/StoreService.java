package com.shop.boutik.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.shop.boutik.model.Store;

public interface StoreService {
	
	
	Optional<Store> findById(Long id);
	
	Optional<Store> findByDesignation(String designation);

	List<Store> findAll();
	
	void save(Store store);

	void delete(Store store);

}
