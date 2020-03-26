package com.shop.boutik.helper.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shop.boutik.helper.dto.shelve.ShelveDto;
import com.shop.boutik.model.Shelve;
import com.shop.boutik.model.Store;
import com.shop.boutik.service.ShelveService;
import com.shop.boutik.service.StoreService;

import lombok.extern.slf4j.Slf4j;

@Component
public class HelperShelve {
	
	
	@Autowired
	private ShelveService shelveServiceNonStatic;
	private static ShelveService shelveService;
	
	@Autowired
	private StoreService storeServiceNonStatic;
	private static StoreService storeService;
	
	@PostConstruct
	public void initStaticDao() {
		
		storeService = this.storeServiceNonStatic;
		shelveService = this.shelveServiceNonStatic;
	}
	
	/**
	 * create new shelve (for develop tests)
	 * @param designation
	 * @param store
	 */
	public static void create(String designation, Store store) {
		
		Shelve shelve = new Shelve();

		shelve.setDesignation(designation);
		shelve.setStore(store);
		
		if (!verifyIfAlreadyExist(shelve)) {

			try {
				shelveService.save(shelve);
			} catch (Exception e) {
				System.out.println(e.getCause());
			}
		} else {
			System.out.println("This shleve designation is already in use");
		}
		
	}
	
	
	/**
	 * create new shelve
	 * @param shelveDto
	 */
	public static void create(ShelveDto shelveDto) {
		
		Shelve shelve = new Shelve();
		
		shelve = parseDtoToModel(shelveDto);
		
		if (!verifyIfAlreadyExist(shelve)) {
			
			try {
				shelveService.save(shelve);
			} catch (Exception e) {
				System.out.println(e.getCause());
			}
		} else {
			System.out.println("This shelve designation is already in use");
		}
		
	}
	
	
	/**
	 * update an existing shelve
	 * @param id
	 * @param shelveDto
	 * @return shelve
	 */
	public static Shelve update(ShelveDto shelveDto) {
		
		Shelve shelve = new Shelve();
		
		Optional<Shelve> shelveOpt = shelveService.findById(shelveDto.getId());
		if (shelveOpt.isPresent()) {
			
			shelve = parseDtoToModel(shelveDto);
			
			if (!verifyIfAlreadyExist(shelve)) {
				
				try {
					shelveService.save(shelve);
				} catch (Exception e) {
					System.out.println(e.getCause());
				}
			} else {
				System.out.println("This shelve designation is already in use");
			}
		}
		return shelve;
	}
	
	
	/**
	 * delete an existing shelve
	 * @param id
	 */
	public static void delete(Long id) {
		
		Optional<Shelve> shelveOpt = shelveService.findById(id);
		if (shelveOpt.isPresent()) {
			
			try {
				shelveService.delete(shelveOpt.get());
			} catch (Exception e) {
				System.out.println("Error while attempt to delete this shelve");
			}
		}
	}
	
	/**
	 * verify if shelve already exist
	 * @param shelve
	 * @return boolean
	 */
	public static boolean verifyIfAlreadyExist(Shelve shelve) {

		return shelveService.findByDesignation(shelve.getDesignation()).isPresent();
	}
	
	
	

	//	PPPPPPP		AAA		RRRRRR	  SSSSSSS	EEEEEEE
	//	PP   PP	   AA AA	RR	 RR	  SS		EE
	//	PP   PP	  AA   AA	RR	 RR	  SS		EE
	//	PPPPPPP	  AAAAAAA	RRRRRR	  SSSSSSS	EEEEEE
	//	PP		  AA   AA	RRRR	   	   SS	EE
	//	PP		  AA   AA	RR	RR		   SS	EE
	//	PP		  AA   AA	RR   RR	  SSSSSSS	EEEEEEE


	/**
	 * parse a Shelve to a ShelveDto
	 * @param Shelve
	 * @return ShelveDto
	 */
	public static ShelveDto parseModelToDto(Shelve shelve) {

		ShelveDto shelveDto = new ShelveDto();

		shelveDto.setId(shelve.getId());
		shelveDto.setIdStore(shelve.getStore().getId());
		shelveDto.setDesignation(shelve.getDesignation());

		return shelveDto;
	}

	
	/**
	 * parse a list of Shelve to list of ShelveDto
	 * @Param List<Shelve>
	 * @return Collection<ShelveDto>
	 */
	public static Collection<ShelveDto> parseListModelToDto(Collection<Shelve> shelves) {

		Collection<ShelveDto> shelvesDto = new ArrayList<ShelveDto>();
		for (Shelve shelve : shelves) {
			shelvesDto.add(parseModelToDto(shelve));
		}

		return shelvesDto;
	}

	/**
	 * parse a ShelveDto to Shelve
	 * @param ShelveDto
	 * @return Shelve
	 */
	public static Shelve parseDtoToModel(ShelveDto shelveDto) {

		Shelve shelve = new Shelve();

		shelve.setId(shelveDto.getId());
		shelve.setStore(storeService.findById(shelveDto.getIdStore()).get());
		shelve.setDesignation(shelveDto.getDesignation());

		return shelve;
	}

}
