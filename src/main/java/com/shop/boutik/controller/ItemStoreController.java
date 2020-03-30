package com.shop.boutik.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.boutik.helper.dto.itemStore.ItemStoreDto;
import com.shop.boutik.helper.util.HelperItemStore;
import com.shop.boutik.model.ItemStore;
import com.shop.boutik.service.ItemStoreService;

@RestController
@RequestMapping("/itemStore")
public class ItemStoreController {
	
	@Autowired
	private ItemStoreService itemStoreService;
	
	
	@GetMapping("/")
	public List<ItemStoreDto> listItemStore() {
		
		Collection<ItemStore> itemStore = itemStoreService.findAll();
		for (ItemStore is : itemStore) {
			System.out.println("id = " + is.getId());
		}
		
		return (List<ItemStoreDto>) HelperItemStore.parseListModelToDto(itemStoreService.findAll());
	}
	
	@GetMapping("{id}")
	public ItemStoreDto plop(@PathVariable("id") Long id) {
		
		return HelperItemStore.parseModelToDto(itemStoreService.findById(id).get());
	}

}
