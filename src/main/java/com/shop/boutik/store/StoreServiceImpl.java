package com.shop.boutik.store;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.boutik.model.Store;

/**
 * Class controller to manage Stores
 * @author Guiot Olivier
 * @version 202003
 */

@Service
public class StoreServiceImpl implements StoreService {

	@Autowired
	private StoreRepository storeRepository;
	

	/**
	 * create promotion for develop test
	 * @param designation
	 */
	@Override
	public void create(String designation) {

		Store store = new Store();

		store.setDesignation(designation);
		
		if (!verifyIfAlreadyExist(store)) {

			try {
				storeRepository.save(store);
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
	@Override
	public void create(StoreDto storeDto) {

		Store store = new Store();

		store = parseDtoToModel(storeDto);
		
		if (!verifyIfAlreadyExist(store)) {

			try {
				storeRepository.save(store);
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
	@Override
	public Store update(StoreDto storeDto) {

		Store store = new Store();

		Optional<Store> storeOpt = findById(storeDto.getId());
		if (storeOpt.isPresent()) {
			
			store = parseDtoToModel(storeDto);
			
			if (!verifyIfAlreadyExist(store)) {

				try {
					storeRepository.save(store);
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
	@Override
	public void delete(Long id) {

		Optional<Store> storeOpt = findById(id);
		if (storeOpt.isPresent()) {
			
			try {
				storeRepository.delete(storeOpt.get());
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
	public boolean verifyIfAlreadyExist(Store store) {

		return storeRepository.findByDesignation(store.getDesignation()).isPresent();
	}



	//	PPPPPPP		AAA		RRRRRR	  SSSSSSS	EEEEEEE
	//	PP   PP	   AA AA	RR	 RR	  SS		EE
	//	PP   PP	  AA   AA	RR	 RR	  SS		EE
	//	PPPPPPP	  AAAAAAA	RRRRRR	  SSSSSSS	EEEEEE
	//	PP		  AA   AA	RRRR	   	   SS	EE
	//	PP		  AA   AA	RR	RR		   SS	EE
	//	PP		  AA   AA	RR   RR	  SSSSSSS	EEEEEEE


	//	PARSE MODEL TO DTO
	/**
	 * parse a Store to a StoreDto
	 * @param Store
	 * @return StoreDto
	 */
	@Override
	public StoreDto parseModelToDto(Store store) {

		StoreDto storeDto = new StoreDto();

		storeDto.setId(store.getId());
		storeDto.setDesignation(store.getDesignation());

		return storeDto;
	}

	
	//	PARSE LIST MODEL TO DTO
	/**
	 * parse a list of Store to list of StoreDto
	 * @Param List<Store>
	 * @return Collection<StoreDto>
	 */
	@Override
	public Collection<StoreDto> parseListModelToDto(Collection<Store> stores) {

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
	//	PARSE DTO TO MODEL
	@Override
	public Store parseDtoToModel(StoreDto storeDto) {

		Store store = new Store();

		store.setId(storeDto.getId());
		store.setDesignation(storeDto.getDesignation());

		return store;
	}

	//	DDDDD		BBBBBB
	//	DD  DD		BB   BB
	//	DD   DD		BB   BB
	//	DD   DD		BBBBBB
	//	DD   DD		BB   BB
	//	DD  DD		BB   BB
	//	DDDDD		BBBBBB

	@Override
	public Optional<Store> findById(Long id) {

		return storeRepository.findById(id);
	}

	@Override
	public List<Store> findAll() {

		return storeRepository.findAll();
	}


	@Override
	public Optional<Store> findByDesignation(String designation) {
		
		return storeRepository.findByDesignation(designation);
	}




}
