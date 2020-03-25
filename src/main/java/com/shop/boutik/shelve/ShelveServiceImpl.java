package com.shop.boutik.shelve;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.boutik.model.Shelve;
import com.shop.boutik.model.Store;
import com.shop.boutik.service.StoreService;

@Service
public class ShelveServiceImpl implements ShelveService {
	
	@Autowired
	private ShelveRepository shelveRepository;
	
	@Autowired
	private StoreService storeService;
	
	
	/**
	 * create new shelve (for develop tests)
	 * @param designation
	 * @param store
	 */
	@Override
	public void create(String designation, Store store) {
		
		Shelve shelve = new Shelve();

		shelve.setDesignation(designation);
		shelve.setStore(store);
		
		if (!verifyIfAlreadyExist(shelve)) {

			try {
				shelveRepository.save(shelve);
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
	@Override
	public void create(ShelveDto shelveDto) {
		
		Shelve shelve = new Shelve();
		
		shelve = parseDtoToModel(shelveDto);
		
		if (!verifyIfAlreadyExist(shelve)) {
			
			try {
				shelveRepository.save(shelve);
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
	@Override
	public Shelve update(ShelveDto shelveDto) {
		
		Shelve shelve = new Shelve();
		
		Optional<Shelve> shelveOpt = findById(shelveDto.getId());
		if (shelveOpt.isPresent()) {
			
			shelve = parseDtoToModel(shelveDto);
			
			if (!verifyIfAlreadyExist(shelve)) {
				
				try {
					shelveRepository.save(shelve);
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
	@Override
	public void delete(Long id) {
		
		Optional<Shelve> shelveOpt = findById(id);
		if (shelveOpt.isPresent()) {
			
			try {
				shelveRepository.delete(shelveOpt.get());
			} catch (Exception e) {
				System.out.println("Error while attempt to delete this shelve");
			}
		}
	}
	
	
	public boolean verifyIfAlreadyExist(Shelve shelve) {

		return shelveRepository.findByDesignation(shelve.getDesignation()).isPresent();
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
	 * parse a Shelve to a ShelveDto
	 * @param Shelve
	 * @return ShelveDto
	 */
	@Override
	public ShelveDto parseModelToDto(Shelve shelve) {

		ShelveDto shelveDto = new ShelveDto();

		shelveDto.setId(shelve.getId());
		shelveDto.setIdStore(shelve.getStore().getId());
		shelveDto.setDesignation(shelve.getDesignation());

		return shelveDto;
	}

	
	//	PARSE LIST MODEL TO DTO
	/**
	 * parse a list of Shelve to list of ShelveDto
	 * @Param List<Shelve>
	 * @return Collection<ShelveDto>
	 */
	@Override
	public Collection<ShelveDto> parseListModelToDto(Collection<Shelve> shelves) {

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
	//	PARSE DTO TO MODEL
	@Override
	public Shelve parseDtoToModel(ShelveDto shelveDto) {

		Shelve shelve = new Shelve();

		shelve.setId(shelveDto.getId());
		shelve.setStore(storeService.findById(shelveDto.getIdStore()).get());
		shelve.setDesignation(shelveDto.getDesignation());

		return shelve;
	}
	

	//	DDDDD		BBBBBB
	//	DD  DD		BB   BB
	//	DD   DD		BB   BB
	//	DD   DD		BBBBBB
	//	DD   DD		BB   BB
	//	DD  DD		BB   BB
	//	DDDDD		BBBBBB

	@Override
	public Optional<Shelve> findById(Long id) {

		return shelveRepository.findById(id);
	}

	@Override
	public List<Shelve> findAll() {

		return shelveRepository.findAll();
	}
	
	@Override
	public List<Shelve> findAllByStoreId(Long storeId) {
		
		return shelveRepository.findAllByStoreId(storeId);
	}


	@Override
	public Optional<Shelve> findByDesignation(String designation) {
		
		return shelveRepository.findByDesignation(designation);
	}



}
