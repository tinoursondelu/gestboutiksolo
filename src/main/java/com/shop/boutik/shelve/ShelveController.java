package com.shop.boutik.shelve;

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

import com.shop.boutik.model.Shelve;

@RestController
@RequestMapping("/shelve")
public class ShelveController {

	@Autowired
	private ShelveService shelveService;


	/**
	 * get all shelve of a store
	 * @param storeId
	 * @return list<ShelveDto>
	 */
	@GetMapping("/all/{storeId}")
	public List<ShelveDto> listShelveByStore(@PathVariable("storeId") Long storeId) {

		List<Shelve> shelves = shelveService.findAllByStoreId(storeId);

		return (List<ShelveDto>) shelveService.parseListModelToDto(shelves);

	}


	/**
	 * get detail of a shelve
	 * @param shelveId
	 * @return ShelveDto
	 */
	@GetMapping("/{shelveId}")
	public ShelveDto shelveDetail(@PathVariable("shelveId") Long shelveId) {

		ShelveDto shelveDto = new ShelveDto();

		Optional<Shelve> shelveOpt = shelveService.findById(shelveId);
		if (shelveOpt.isPresent()) {

			shelveDto = shelveService.parseModelToDto(shelveOpt.get());

		} else {
			System.out.println("Shelve with id '" + shelveId + "' not found");
		}
		return shelveDto;
	}


	/**
	 * create a new shelve
	 * @param shelveDto
	 */
	@PostMapping("/create") 
	public void createShelve(@RequestBody ShelveDto shelveDto) {

		shelveService.create(shelveDto);
	}


	/**
	 * update an existing shelve
	 * @param shelveDto
	 * @return
	 */
	@PutMapping("/update")
	public ShelveDto updateShelve(@RequestBody ShelveDto shelveDto) {

		return shelveService.parseModelToDto(shelveService.update(shelveDto));
	}


	/**
	 * delete a shelve
	 * @param shelveDto
	 */
	@DeleteMapping("/delete")
	public void deleteShelve(@RequestBody ShelveDto shelveDto) {

		shelveService.delete(shelveDto.getId());
	}

}
