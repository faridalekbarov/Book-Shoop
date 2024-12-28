package com.example.Book_Shopping.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.Book_Shopping.dao.entity.Courier;
import com.example.Book_Shopping.model.CourierDto;

@Component
public class CourierMapper {

	public CourierDto entityToDto(Courier courier) {
		CourierDto courierDto = new CourierDto();
		courierDto.setId(courier.getId());
		courierDto.setName(courier.getName());
		return courierDto;		
	}
	
	public List<CourierDto> entityListToDtoList(List<Courier> couriers){
		List<CourierDto> courierDtos = new ArrayList<>();
		couriers.stream()
		.forEach(it ->{
			CourierDto courierDto = entityToDto(it);
			courierDtos.add(courierDto);
		});
		return courierDtos;
	}
	
	public Courier dtoToEntity(CourierDto courierDto) {
		Courier courier = new Courier();
		courier.setId(courierDto.getId());
		courier.setName(courierDto.getName());
		return courier;
	}
}