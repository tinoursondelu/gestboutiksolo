package com.shop.boutik.helper.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shop.boutik.helper.dto.itemStore.ItemStoreDto;
import com.shop.boutik.model.Brand;
import com.shop.boutik.model.Color;
import com.shop.boutik.model.Item;
import com.shop.boutik.model.ItemStore;
import com.shop.boutik.model.Size;
import com.shop.boutik.service.BrandServiceImpl;
import com.shop.boutik.service.ColorServiceImpl;
import com.shop.boutik.service.ItemService;
import com.shop.boutik.service.ItemStoreService;
import com.shop.boutik.service.SizeServiceImpl;

@Component
@Transactional
public class HelperItemStore {

	@Autowired
	private ItemStoreService itemStoreServiceNonStatic;
	private static ItemStoreService itemStoreService;

	@Autowired
	private ItemService itemServiceNonStatic;
	private static ItemService itemService;

	@Autowired
	private BrandServiceImpl brandServiceNonStatic;
	private static BrandServiceImpl brandService;

	@Autowired
	private ColorServiceImpl colorServiceNonStatic;
	private static ColorServiceImpl colorService;

	@Autowired
	private SizeServiceImpl sizeServiceNonStatic;
	private static SizeServiceImpl sizeService;



	@PostConstruct
	public void initStaticDao() {

		itemStoreService = this.itemStoreServiceNonStatic;
		itemService = this.itemServiceNonStatic;
		brandService = this.brandServiceNonStatic;
		colorService = this.colorServiceNonStatic;
		sizeService = this.sizeServiceNonStatic;
	}

	public static void create(Long idItem, Long idBrand, Long idColor, Long idSize) {

		ItemStore itemStore = new ItemStore();
		Item item = itemService.findById(idItem).get();
		Brand brand = brandService.findById(idBrand).get();
		Color color = colorService.findById(idColor).get();
		Size size = sizeService.findById(idSize).get();

		itemStore.setItem(item);
		itemStore.setBrand(brand);
		itemStore.setColor(color);
		itemStore.setSize(size);
		itemStore.setDesignation(createDesignation(itemStore));

		if (!verifyIfAlreadyExist(itemStore)) {

			try {
				itemStoreService.save(itemStore);
			} catch (Exception e) {
				System.out.println(e.getCause());
			}
		} else {
			System.out.println("This itemStore already exist");
		}
	}

	public static void create(ItemStoreDto itemStoreDto) {

		ItemStore itemStore = new ItemStore();

		itemStore.setItem(itemService.findById(itemStoreDto.getIdItem()).get());
		itemStore.setBrand(brandService.findById(itemStoreDto.getIdBrand()).get());
		itemStore.setColor(colorService.findById(itemStoreDto.getIdColor()).get());
		itemStore.setSize(sizeService.findById(itemStoreDto.getIdSize()).get());
		itemStore.setDesignation(createDesignation(itemStore));

		if (!verifyIfAlreadyExist(itemStore)) {

			try {
				itemStoreService.save(itemStore);
			} catch (Exception e) {
				System.out.println(e.getCause());
			}
		} else {
			System.out.println("This itemStore already exist");
		}

	}

	public static ItemStore update(ItemStoreDto itemStoreDto) {

		ItemStore itemStore = new ItemStore();

		Optional<ItemStore> dbItemStore = itemStoreService.findById(itemStoreDto.getId());
		if (dbItemStore.isPresent()) {
// TODO: add method for verify with exclusion
			itemStore = parseDtoToModel(itemStoreDto);

			if (!verifyIfAlreadyExist(itemStore)) {

				try {
					itemStoreService.save(itemStore);
				} catch (Exception e) {
					System.out.println(e.getCause());
				}
			} System.out.println("This itemStore already exist");
		}
		return itemStore;
	}

	public static void delete(Long id) {

		Optional<ItemStore> itemStore = itemStoreService.findById(id);
		if (itemStore.isPresent()) {
			
			try {
				itemStoreService.delete(itemStore.get());
			} catch (Exception e) {
				System.out.println(e.getCause());
			}
		}
	}

	public static String createDesignation(ItemStore itemStore) {

		String designation = "";

		if (itemStore.getBrand() != null && itemStore.getColor() != null && itemStore.getSize() != null 
				&& itemStore.getItem() != null) {

			designation = (itemStore.getItem().getDesignation() + " " + itemStore.getBrand().getLabel() + " "
					+ itemStore.getColor().getLabel() + " " + itemStore.getSize().getLabel());
		}
		return designation;
	}

	public static boolean verifyIfAlreadyExist(ItemStore itemStore) {

		return itemStoreService.finByDesignation(itemStore.getDesignation()).isPresent();
	}


	//	PPPPPPP		AAA		RRRRRR	  SSSSSSS	EEEEEEE
	//	PP   PP	   AA AA	RR	 RR	  SS		EE
	//	PP   PP	  AA   AA	RR	 RR	  SS		EE
	//	PPPPPPP	  AAAAAAA	RRRRRR	  SSSSSSS	EEEEEE
	//	PP		  AA   AA	RRRR	   	   SS	EE
	//	PP		  AA   AA	RR	RR		   SS	EE
	//	PP		  AA   AA	RR   RR	  SSSSSSS	EEEEEEE


	public static ItemStoreDto parseModelToDto(ItemStore itemStore) {

		ItemStoreDto itemStoreDto = new ItemStoreDto();

		itemStoreDto.setId(itemStore.getId());
		itemStoreDto.setIdItem(itemStore.getItem().getId());
		itemStoreDto.setIdBrand(itemStore.getBrand().getId());
		itemStoreDto.setIdColor(itemStore.getColor().getId());
		itemStoreDto.setIdSize(itemStore.getSize().getId());
		itemStoreDto.setDesignation(itemStore.getDesignation());

		return itemStoreDto;
	}


	public static Collection<ItemStoreDto> parseListModelToDto(Collection<ItemStore> itemsStore) {

		Collection<ItemStoreDto> itemsStoreDto = new ArrayList<ItemStoreDto>();
		for (ItemStore itemStore : itemsStore) {
			itemsStoreDto.add(parseModelToDto(itemStore));
		}
		return itemsStoreDto;
	}


	public static ItemStore parseDtoToModel(ItemStoreDto itemStoreDto) {

		ItemStore itemStore = new ItemStore();

		itemStore.setId(itemStoreDto.getId());
		itemStore.setItem(itemService.findById(itemStoreDto.getIdItem()).get());
		itemStore.setBrand(brandService.findById(itemStoreDto.getIdBrand()).get());
		itemStore.setColor(colorService.findById(itemStoreDto.getIdColor()).get());
		itemStore.setSize(sizeService.findById(itemStoreDto.getIdSize()).get());
		itemStore.setDesignation(itemStoreDto.getDesignation());

		return itemStore;
	}

}
