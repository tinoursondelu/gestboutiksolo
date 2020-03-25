package com.shop.boutik.service;

import java.util.Optional;

public interface CharacteristicService {
	
	Optional<?> findById(Long id);
	
	Optional<?> findByLabel(String label);

}
