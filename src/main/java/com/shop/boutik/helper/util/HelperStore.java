package com.shop.boutik.helper.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shop.boutik.helper.dto.store.StoreDto;
import com.shop.boutik.model.Store;
import com.shop.boutik.service.StoreService;

@Component
public class HelperStore {



	@Autowired
	private StoreService storeServiceNonStatic;
	private static StoreService storeService;

	@PostConstruct
	public void initStaticDao() {

		storeService = this.storeServiceNonStatic;
	}


	/**
	 * create promotion for develop test
	 * @param designation
	 */
	public static void create(String designation) {

		Store store = new Store();

		store.setDesignation(designation);

		if (!verifyIfAlreadyExist(store)) {

			try {
				storeService.save(store);
			} catch (Exception e) {
				System.out.println(e.getCause());
			}
		} else {
			System.out.println("This store designation is already in use");
		}
	}


	/**
	 * create Store with a StoreDto
	 * @param StoreDto
	 */
	public static void create(StoreDto storeDto) {

		Store store = new Store();

		store = parseDtoToModel(storeDto);

		if (!verifyIfAlreadyExist(store)) {

			try {
				storeService.save(store);
			} catch (Exception e) {
				System.out.println(e.getCause());
			}
		} else {
			System.out.println("This store designation is already in use");
		}
	}


	/**
	 * update Store 
	 * @param idStore
	 * @param StoreDto
	 * @return Store
	 */
	public static Store update(StoreDto storeDto) {

		Store store = new Store();

		Optional<Store> storeOpt = storeService.findById(storeDto.getId());
		if (storeOpt.isPresent()) {

			store = parseDtoToModel(storeDto);

			if (!verifyIfAlreadyExistWithExclusion(store)) {

				try {
					storeService.save(store);
				} catch (Exception e) {
					System.out.println(e.getCause());
				}
			} else {
				System.out.println("This store designation is already in use");
			}
		}
		return store;
	}


	/**
	 * delete a store
	 * @param idStore
	 */
	public static void delete(Long id) {

		Optional<Store> storeOpt = storeService.findById(id);
		if (storeOpt.isPresent()) {

			try {
				storeService.delete(storeOpt.get());
			} catch (Exception e) {
				System.out.println("Error while attempt to delete this shelve");
			}

		}
	}


	/**
	 * method for verify if a store exist (by designation)
	 * @param store
	 * @return boolean
	 */
	public static boolean verifyIfAlreadyExist(Store store) {

		Optional<Store> storeOpt = storeService.findByDesignation(store.getDesignation());

		return storeOpt.isPresent();
	}

	static boolean verifyIfAlreadyExistWithExclusion(Store store) {

		Optional<Store> dbStore = storeService.findByDesignation(store.getDesignation());
		
		if (dbStore.isPresent()) {

			if (dbStore.get().getId() == store.getId()) {
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}


	/**
	 * parse a Store to a StoreDto
	 * @param Store
	 * @return StoreDto
	 */
	public static StoreDto parseModelToDto(Store store) {

		StoreDto storeDto = new StoreDto();

		storeDto.setId(store.getId());
		storeDto.setDesignation(store.getDesignation());

		return storeDto;
	}


	/**
	 * parse a list of Store to list of StoreDto
	 * @Param List<Store>
	 * @return Collection<StoreDto>
	 */
	public static Collection<StoreDto> parseListModelToDto(Collection<Store> stores) {

		Collection<StoreDto> storesDto = new ArrayList<StoreDto>();
		for (Store store : stores) {
			storesDto.add(parseModelToDto(store));
		}

		return storesDto;
	}

	/**
	 * parse a StoreDto to Store
	 * @param StoreDto
	 * @return Store
	 */
	public static Store parseDtoToModel(StoreDto storeDto) {

		Store store = new Store();

		store.setId(storeDto.getId());
		store.setDesignation(storeDto.getDesignation());

		return store;
	}


}
