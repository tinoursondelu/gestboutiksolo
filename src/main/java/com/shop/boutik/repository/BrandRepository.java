package com.shop.boutik.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shop.boutik.model.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
	
	Optional<Brand> findById(Long id);

	Optional<Brand> findByLabel(String label);

}
