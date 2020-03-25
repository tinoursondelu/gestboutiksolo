package com.shop.boutik.shelve;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.shop.boutik.model.Shelve;
import com.shop.boutik.model.Store;

public interface ShelveService {
	
	
//	METHODS
	public void create(String designation, Store store);
	
	public void create(ShelveDto shelveDto);
	
	public Shelve update(ShelveDto shelveDto);
	
	public void delete(Long id);	
	
	public ShelveDto parseModelToDto(Shelve shelve);
	
	public Collection<ShelveDto> parseListModelToDto(Collection<Shelve> shelves);
	
	public Shelve parseDtoToModel(ShelveDto shelveDto);

	
//	DATA BASE
	public Optional<Shelve> findById(Long id);
	
	Optional<Shelve> findByDesignation(String designation);
	
	public List<Shelve> findAll();
	
	public List<Shelve> findAllByStoreId(Long storeId);

}
