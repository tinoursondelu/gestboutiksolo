package com.shop.boutik.shelve;

import java.util.List;
import java.util.Optional;

import com.shop.boutik.model.Shelve;

public interface ShelveService {
	
	public Optional<Shelve> findById(Long id);
	
	Optional<Shelve> findByDesignation(String designation);
	
	public List<Shelve> findAll();
	
	public List<Shelve> findAllByStoreId(Long storeId);

	public void save(Shelve shelve);

	public void delete(Shelve shelve);

}
