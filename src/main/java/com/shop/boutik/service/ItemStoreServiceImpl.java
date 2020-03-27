package com.shop.boutik.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.boutik.model.Item;
import com.shop.boutik.repository.ItemStoreRepository;

@Service
public class ItemStoreServiceImpl implements ItemStoreService{
	
	@Autowired
	private ItemStoreRepository itemStoreRepository;

	

}
