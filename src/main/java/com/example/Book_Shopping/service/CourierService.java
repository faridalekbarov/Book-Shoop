package com.example.Book_Shopping.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Book_Shopping.dao.entity.Courier;
import com.example.Book_Shopping.dao.repository.CourierRepository;
import com.example.Book_Shopping.exception.BadRequestException;
import com.example.Book_Shopping.exception.NotFoundException;
import com.example.Book_Shopping.mapper.CourierMapper;
import com.example.Book_Shopping.model.CourierDto;

@Service
public class CourierService {

	@Autowired
	private CourierRepository courierRepository;

	@Autowired
	private CourierMapper courierMapper;

	public List<CourierDto> getAllCouriers() {
		List<Courier> couriers = courierRepository.findAll();
		List<CourierDto> courierDtos = courierMapper.entityListToDtoList(couriers);
		return courierDtos;
	}

	public CourierDto getById(Long id) {
		Courier courier = courierRepository.findById(id).orElseThrow(() -> {
			throw new NotFoundException("Courier doesn't exist with given id");
		});
		CourierDto courierDto = courierMapper.entityToDto(courier);
		return courierDto;
	}

	public CourierDto createCourier(CourierDto courierDto) {
		if (courierDto == null) {
			throw new BadRequestException("Courier create request can't be null");
		}
		Courier entity = courierMapper.dtoToEntity(courierDto);
		courierRepository.save(entity);
		return courierDto;
	}

	public CourierDto updateCourier(CourierDto courierDto, Long id) {
		Courier courier = courierRepository.findById(id).orElseThrow(() -> {
			throw new NotFoundException("Courier doesn't exist with given id");
		});
		courier.setId(courierDto.getId());
		courier.setName(courierDto.getName());
		courierRepository.save(courier);
		CourierDto response = courierMapper.entityToDto(courier);
		return response;
	}

}
