package com.shop.boutik.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shop.boutik.model.Size;

@Repository
public interface SizeRepository extends JpaRepository<Size, Long> {
	
	Optional<Size> findById(Long id);

	Optional<Size> findByLabel(String label);

}
