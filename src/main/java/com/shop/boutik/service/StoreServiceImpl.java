package com.shop.boutik.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.boutik.model.Store;
import com.shop.boutik.repository.StoreRepository;

/**
 * Class controller to manage Stores
 * @author Guiot Olivier
 * @version 202003
 */

@Service
public class StoreServiceImpl implements StoreService {

	@Autowired
	private StoreRepository storeRepository;



	@Override
	public Optional<Store> findById(Long id) {

		return storeRepository.findById(id);
	}

	@Override
	public List<Store> findAll() {

		return storeRepository.findAll();
	}


	@Override
	public Optional<Store> findByDesignation(String designation) {
		
		return storeRepository.findByDesignation(designation);
	}

	@Override
	public void save(Store store) {
		
		storeRepository.save(store);		
	}

	@Override
	public void delete(Store store) {
		
		storeRepository.delete(store);
	}




}
