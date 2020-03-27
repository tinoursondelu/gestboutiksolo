package com.shop.boutik.controller;

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

import com.shop.boutik.helper.dto.item.ItemDto;
import com.shop.boutik.helper.util.HelperItem;
import com.shop.boutik.model.Item;
import com.shop.boutik.service.ItemService;

@RestController
@RequestMapping("/item")
public class ItemController {
	
	@Autowired
	private ItemService itemService;

	/**
	 * get list of items
	 * @return list of itemDto
	 */
	@GetMapping("/")
	public List<ItemDto> listItem() {
		
		List<Item> dbItems = itemService.findAll();
		
		return (List<ItemDto>) HelperItem.parseListModelToDto(dbItems);
	}
	
	
	/**
	 * get detail of item
	 * @param itemId
	 * @return ItemDto
	 */
	@GetMapping("/{itemId}")
	public ItemDto itemDetail(@PathVariable("itemId") Long itemId) {
		
		ItemDto itemDto = new ItemDto();
		
		Optional<Item> dbItem = itemService.findById(itemId);
		if (dbItem.isPresent()) {
			
			itemDto = HelperItem.parseModelToDto(dbItem.get());
		} else {
			System.out.println("item with id '" + itemId + "' not found");
		}
		return itemDto;
	}
	
	/**
	 * create an item
	 * @param itemDto
	 */
	@PostMapping("/create")
	public void create(@RequestBody ItemDto itemDto) {
		
		HelperItem.create(itemDto);
	}
	
	/**
	 * update an item
	 * @param itemDto
	 * @return itemDto
	 */
	@PutMapping("/update")
	public ItemDto update(@RequestBody ItemDto itemDto) {
		
		return HelperItem.parseModelToDto(HelperItem.update(itemDto));
	}
	
	/**
	 * delete an item
	 * @param itemDto
	 */
	@DeleteMapping("/delete")
	public void delete(@RequestBody ItemDto itemDto) {
		
		HelperItem.delete(itemDto);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
