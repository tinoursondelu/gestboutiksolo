package com.shop.boutik.helper.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shop.boutik.helper.dto.item.ItemDto;
import com.shop.boutik.model.Item;
import com.shop.boutik.service.ItemService;

@Component
public class HelperItem {

	@Autowired
	private ItemService itemServiceNonStatic;
	private static ItemService itemService;

	@PostConstruct
	public void initStaticDao() {

		itemService = this.itemServiceNonStatic;
	}


	public static void create(String designation) {

		Item item = new Item();

		item.setDesignation(designation);

		if (!verifyIfAlreadyExist(item)) {

			try {
				itemService.save(item);
			} catch (Exception e) {
				System.out.println(e.getCause());
			}
		} else {
			System.out.println("This item already exist");
		}

	}

	public static void create(ItemDto itemDto) {

		Item item = new Item();

		item.setId(itemDto.getId());
		item.setDesignation(itemDto.getDesignation());

		if (!verifyIfAlreadyExist(item)) {

			try {
				itemService.save(item);
			} catch (Exception e) {
				System.out.println(e.getCause());
			}
		} else {
			System.out.println("This item already exist");
		}

	}

	
	public static Item update(ItemDto itemDto) {
		
		Item item = new Item();
		
		Optional<Item> itemOpt = itemService.findById(itemDto.getId());
		if (itemOpt.isPresent()) {
			
			item = parseDtoToModel(itemDto);
			
			if (!verifyIfAlreadyExistWithExclusion(item)) {
				
				try {
					itemService.save(item);
				} catch (Exception e) {
					System.out.println(e.getCause());
				}
			} else {
				System.out.println("This item designation is already in use");
			}
		}
		return item;
	}
	
	public static void delete(ItemDto itemDto) {
		
		Optional<Item> dbItem = itemService.findById(itemDto.getId());
		if (dbItem.isPresent()) {
			
			try {
				itemService.delete(dbItem.get());
			} catch (Exception e) {
				System.out.println("Error atempt to delete this item");
			}
		}
	}
	
	public static boolean verifyIfAlreadyExist(Item item) {

		return itemService.findByDesignation(item.getDesignation()).isPresent();
	}
	
	public static boolean verifyIfAlreadyExistWithExclusion(Item item) {
		
		Optional<Item> dbItem = itemService.findByDesignation(item.getDesignation());
		
		if (dbItem.isPresent()) {
			
			if (dbItem.get().getId() == item.getId()) {
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}
		
	}

	//	PPPPPPP		AAA		RRRRRR	  SSSSSSS	EEEEEEE
	//	PP   PP	   AA AA	RR	 RR	  SS		EE
	//	PP   PP	  AA   AA	RR	 RR	  SS		EE
	//	PPPPPPP	  AAAAAAA	RRRRRR	  SSSSSSS	EEEEEE
	//	PP		  AA   AA	RRRR	   	   SS	EE
	//	PP		  AA   AA	RR	RR		   SS	EE
	//	PP		  AA   AA	RR   RR	  SSSSSSS	EEEEEEE


	/**
	 * parse an item entity to item dto
	 * @param item
	 * @return ItemDto
	 */
	public static ItemDto parseModelToDto(Item item) {

		ItemDto itemDto = new ItemDto();

		itemDto.setId(item.getId());
		itemDto.setDesignation(item.getDesignation());

		return itemDto;
	}

	/**
	 * parse list of item entity to list of item dto
	 * @param items
	 * @return Collection<ItemDto>
	 */
	public static Collection<ItemDto> parseListModelToDto(Collection<Item> items) {

		Collection<ItemDto> itemsDto = new ArrayList<ItemDto>();

		for (Item item : items) {
			itemsDto.add(parseModelToDto(item));
		}
		return itemsDto;
	}

	/**
	 * parse an item dto to entity
	 * @param itemsStore
	 * @return Item
	 */
	public static Item parseDtoToModel(ItemDto itemDto) {

		Item item = new Item();

		item.setId(itemDto.getId());
		item.setDesignation(itemDto.getDesignation());

		return item;
	}



























}
