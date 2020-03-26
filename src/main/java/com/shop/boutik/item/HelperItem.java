package com.shop.boutik.item;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shop.boutik.model.Item;

@Component
public class HelperItem {

	@Autowired
	private ItemService itemServiceNonStatic;
	private static ItemService itemService;

	@PostConstruct
	public void initStaticDao() {

		itemService = this.itemServiceNonStatic;
	}


	static void create(String designation) {

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

	static void create(ItemDto itemDto) {

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

	
	static Item update(ItemDto itemDto) {
		
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
	
	static void delete(Long id) {
		
		Optional<Item> dbItem = itemService.findById(id);
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
	
	static boolean verifyIfAlreadyExistWithExclusion(Item item) {
		
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
	static ItemDto parseModelToDto(Item item) {

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
	static Collection<ItemDto> parseListModelToDto(Collection<Item> items) {

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
	static Item parseDtoToModel(ItemDto itemDto) {

		Item item = new Item();

		item.setId(itemDto.getId());
		item.setDesignation(itemDto.getDesignation());

		return item;
	}



























}
