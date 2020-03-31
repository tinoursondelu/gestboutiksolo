package com.shop.boutik.controller;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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


//	TODO: securiser update et delete
	
	@GetMapping("/")
	public List<ItemStoreDto> listItemStore() {

		Collection<ItemStore> itemStore = itemStoreService.findAll();
		for (ItemStore is : itemStore) {
			System.out.println("id = " + is.getId());
		}

		return (List<ItemStoreDto>) HelperItemStore.parseListModelToDto(itemStoreService.findAll());
	}

	@GetMapping("/{itemStoreId}")
	public ItemStoreDto plop(@PathVariable("itemStoreId") Long itemStoreId) {

		ItemStoreDto itemStoreDto = new ItemStoreDto();

		Optional<ItemStore> dbItemStore = itemStoreService.findById(itemStoreId);
		if (dbItemStore.isPresent()) {

			itemStoreDto = HelperItemStore.parseModelToDto(dbItemStore.get());
		} else {
			System.out.println("itemStore with id '" + itemStoreId + "' not found");
		}
		return itemStoreDto;
	}

	@PostMapping("/create")
	public void create(@RequestBody ItemStoreDto itemStoreDto) {
System.out.println("c'est parti");
		HelperItemStore.create(itemStoreDto);
	}

	@PutMapping("/update")
	public ItemStoreDto update(@RequestBody ItemStoreDto itemStoreDto) {

		return HelperItemStore.parseModelToDto(HelperItemStore.update(itemStoreDto));
	}

	@DeleteMapping("/delete")
	public void delete(@RequestBody ItemStoreDto itemStoreDto) {

		HelperItemStore.delete(itemStoreDto.getId());
	}








}
