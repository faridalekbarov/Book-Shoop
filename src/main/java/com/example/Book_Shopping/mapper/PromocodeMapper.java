package com.example.Book_Shopping.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.Book_Shopping.dao.entity.Promocode;
import com.example.Book_Shopping.model.PromocodeDto;

@Component
public class PromocodeMapper {

	public PromocodeDto entityToDto(Promocode promocode) {
		PromocodeDto promocodeDto = new PromocodeDto();
		promocodeDto.setId(promocode.getId());
		promocodeDto.setCode(promocode.getCode());
		promocodeDto.setDiscountPercentage(promocode.getDiscountPercentage());
		return promocodeDto;
	}

	public List<PromocodeDto> entityListToDtoList(List<Promocode> promocodes) {
		List<PromocodeDto> promocodeDtos = new ArrayList<>();
		promocodes.stream().forEach(it -> {
			PromocodeDto promocodeDto = entityToDto(it);
			promocodeDtos.add(promocodeDto);
		});
		return promocodeDtos;
	}

	public Promocode dtoToEntity(PromocodeDto promocodeDto) {
		Promocode promocode = new Promocode();
		promocode.setId(promocodeDto.getId());
		promocode.setCode(promocodeDto.getCode());
		promocode.setDiscountPercentage(promocodeDto.getDiscountPercentage());
		return promocode;
	}
}
