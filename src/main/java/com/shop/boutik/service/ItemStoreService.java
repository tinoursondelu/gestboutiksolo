package com.shop.boutik.service;

import java.util.Collection;
import java.util.Optional;

import com.shop.boutik.model.ItemStore;

public interface ItemStoreService {

	void save(ItemStore itemStore);

	Optional<ItemStore> findById(Long id);

	Optional<ItemStore> finByDesignation(String designation);

	void delete(ItemStore itemStore);

	Collection<ItemStore> findAll();


}
