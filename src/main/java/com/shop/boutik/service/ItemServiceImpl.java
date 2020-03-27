package com.shop.boutik.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.boutik.model.Item;
import com.shop.boutik.repository.ItemRepository;

@Service
public class ItemServiceImpl implements ItemService {
	
	
	@Autowired
	private ItemRepository itemRepository;

	@Override
	public Optional<Item> findByDesignation(String designation) {
		
		return itemRepository.findByDesignation(designation);
	}
	
	@Override
	public Optional<Item> findById(Long id) {

		return itemRepository.findById(id);
	}

	@Override
	public void save(Item item) {

		itemRepository.save(item);
	}

	@Override
	public void delete(Item item) {

		itemRepository.delete(item);
	}

	@Override
	public List<Item> findAll() {
		
		return itemRepository.findAll();
	}



}
