package com.shop.boutik.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shop.boutik.model.Shelve;

@Repository
public interface ShelveRepository extends JpaRepository<Shelve, Long> {

	public List<Shelve> findAllByStoreId(Long storeId);
	
	Optional<Shelve> findByDesignation(String designation);

	public void deleteByItemsStoreId(Long idItemStore);

}
