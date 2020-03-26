package com.shop.boutik.item;

import java.util.Optional;

import com.shop.boutik.model.Item;

public interface ItemService {

	Optional<Item> findByDesignation(String designation);
	
	Optional<Item> findById(Long id);

	void save(Item item);

	void delete(Item item);

	

}
