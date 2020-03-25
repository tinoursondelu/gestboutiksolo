package com.shop.boutik.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shop.boutik.model.Color;

@Repository
public interface ColorRepository extends JpaRepository<Color, Long> {
	
	Optional<Color> findById(Long id);

	Optional<Color> findByLabel(String label);

}
