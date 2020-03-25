package com.shop.boutik.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.boutik.model.Color;
import com.shop.boutik.repository.ColorRepository;

@Service
public class ColorServiceImpl implements CharacteristicService {
	
	@Autowired
	private ColorRepository colorRepository;

	@Override
	public Optional<Color> findById(Long id) {
		
		return colorRepository.findById(id);
	}

	@Override
	public Optional<Color> findByLabel(String label) {
		
		return colorRepository.findByLabel(label);
	}

}
