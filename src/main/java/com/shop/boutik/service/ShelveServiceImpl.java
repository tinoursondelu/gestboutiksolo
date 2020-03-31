package com.shop.boutik.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.boutik.model.Shelve;
import com.shop.boutik.repository.ShelveRepository;

@Service
public class ShelveServiceImpl implements ShelveService {
	
	@Autowired
	private ShelveRepository shelveRepository;
	

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

	@Override
	public void save(Shelve shelve) {

		shelveRepository.save(shelve);
	}

	@Override
	public void delete(Shelve shelve) {

		shelveRepository.delete(shelve);
	}

	public void deleteByItemsStoreId(Long idItemStore) {
System.out.println("hohohohohoho");
		shelveRepository.deleteByItemsStoreId(idItemStore);
	}



}
