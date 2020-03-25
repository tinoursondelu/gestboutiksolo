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

import com.shop.boutik.helper.dto.store.StoreDto;
import com.shop.boutik.helper.util.HelperStore;
import com.shop.boutik.model.Store;
import com.shop.boutik.service.StoreService;

@RestController
@RequestMapping("/store")
public class StoreController {
	
	@Autowired
	private StoreService storeService;
	
	
	/**
	 * get list of stores
	 * @return List<StoreDto>
	 */
	@GetMapping("/")
	public List<StoreDto> listStores() {
		
		List<Store> stores = storeService.findAll();
		
		return (List<StoreDto>) HelperStore.parseListModelToDto(stores);
	}
	
	/**
	 * get detail Store
	 * @param storeId
	 * @return StoreDto
	 */
	@GetMapping("/{storeId}")
	public StoreDto storeDetail(@PathVariable("storeId") Long storeId) {
		
		StoreDto storeDto = new StoreDto();
		
		Optional<Store> storeOpt = storeService.findById(storeId);
		if (storeOpt.isPresent()) {
			storeDto = HelperStore.parseModelToDto(storeOpt.get());
		}
		else {
			System.out.println("Store with id '" + storeId + "' not found");
		}
		
		return storeDto;
	}
	
	/**
	 * create new store
	 * @param storeDto
	 */
	@PostMapping("/create")
	public void createStore(@RequestBody StoreDto storeDto) {
		
		HelperStore.create(storeDto);
	}
	
	/**
	 * delete a store
	 * @param storeDto
	 */
	@DeleteMapping("/delete")
	public void deleteStore(@RequestBody StoreDto storeDto) {
		
		HelperStore.delete(storeDto.getId());
		
	}
	
	/**
	 * update a store
	 * @param storeId
	 * @param storeDto
	 * @return Store
	 */
	@PutMapping("/update")
	public Store updateStore(@RequestBody StoreDto storeDto) {
		
		return HelperStore.update(storeDto);
	}

}
