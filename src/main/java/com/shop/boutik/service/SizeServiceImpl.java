package com.shop.boutik.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.boutik.model.Size;
import com.shop.boutik.repository.SizeRepository;

@Service
public class SizeServiceImpl implements CharacteristicService {
	
	@Autowired
	private SizeRepository sizeRepository;

	@Override
	public Optional<Size> findById(Long id) {
		
		return sizeRepository.findById(id);
	}

	@Override
	public Optional<Size> findByLabel(String label) {

		return sizeRepository.findByLabel(label);
	}

}
