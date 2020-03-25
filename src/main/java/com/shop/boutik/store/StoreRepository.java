package com.shop.boutik.store;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shop.boutik.model.Store;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {

	Optional<Store> findByDesignation(String designation);
	
	

}
