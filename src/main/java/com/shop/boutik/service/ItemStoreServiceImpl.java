package com.shop.boutik.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.boutik.model.ItemStore;
import com.shop.boutik.repository.ItemStoreRepository;

@Service
public class ItemStoreServiceImpl implements ItemStoreService{
	
	@Autowired
	private ItemStoreRepository itemStoreRepository;

	@Override
	public void save(ItemStore itemStore) {
		
		itemStoreRepository.save(itemStore);		
	}

	@Override
	public Optional<ItemStore> findById(Long id) {
		
		return itemStoreRepository.findById(id);
	}

	@Override
	public Optional<ItemStore> finByDesignation(String designation) {

		return itemStoreRepository.findByDesignation(designation);
	}

	@Override
	public void delete(ItemStore itemStore) {

		itemStoreRepository.delete(itemStore);		
	}

	@Override
	public Collection<ItemStore> findAll() {

		return itemStoreRepository.findAll();
	}

	

}
