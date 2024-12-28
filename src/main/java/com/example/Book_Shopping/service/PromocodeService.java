package com.example.Book_Shopping.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Book_Shopping.dao.entity.Order;
import com.example.Book_Shopping.dao.entity.Promocode;
import com.example.Book_Shopping.dao.repository.PromocodeRepository;
import com.example.Book_Shopping.exception.BadRequestException;
import com.example.Book_Shopping.exception.NotFoundException;
import com.example.Book_Shopping.mapper.PromocodeMapper;
import com.example.Book_Shopping.model.PromocodeDto;

@Service
public class PromocodeService {

	@Autowired
	private PromocodeRepository promocodeRepository;

	@Autowired
	private PromocodeMapper promocodeMapper;

	public Optional<Promocode> findByCode(String code) {
		return promocodeRepository.findByCode(code);
	}

	public void assignPromocodeToOrder(Promocode promocode, Order order) {
		if (promocode != null && order != null) {
			order.setPromocode(promocode.getCode());
		} else {
			throw new IllegalArgumentException("Promocode or Order cannot be null");
		}
	}

	public Double applyPromocodeDiscount(Double totalPrice, String code) {
		Promocode promocode = promocodeRepository.findByCode(code)
				.orElseThrow(() -> new NotFoundException("Invalid promocode"));
		Double discount = (totalPrice * promocode.getDiscountPercentage()) / 100;
		Double discountedPrice = totalPrice - discount;
		return discountedPrice;
	}

	public Double calculateDiscount(Double totalPrice, String code) {
		Promocode promocode = promocodeRepository.findByCode(code)
				.orElseThrow(() -> new NotFoundException("Invalid promocode"));
		Double discount = (totalPrice * promocode.getDiscountPercentage()) / 100;
		return discount;
	}

	public List<PromocodeDto> getAllPromocodes() {
		List<Promocode> promocodes = promocodeRepository.findAll();
		List<PromocodeDto> promocodeDtos = promocodeMapper.entityListToDtoList(promocodes);
		return promocodeDtos;
	}

	public PromocodeDto getById(Long id) {
		Promocode promocode = promocodeRepository.findById(id).orElseThrow(() -> {
			throw new NotFoundException("Promocode doesn't exist with given id");
		});
		PromocodeDto promocodeDto = promocodeMapper.entityToDto(promocode);
		return promocodeDto;
	}

	public PromocodeDto createPromocode(PromocodeDto promocodeDto) {
		if (promocodeDto == null) {
			throw new BadRequestException("Promocode create request can't be null");
		}
		Promocode entity = promocodeMapper.dtoToEntity(promocodeDto);
		promocodeRepository.save(entity);
		return promocodeDto;
	}

	public PromocodeDto updatePromocode(PromocodeDto promocodeDto, Long id) {
		Promocode promocode = promocodeRepository.findById(id).orElseThrow(() -> {
			throw new NotFoundException("Promocode doesn't exist with given id");
		});
		promocode.setCode(promocodeDto.getCode());
		promocode.setDiscountPercentage(promocodeDto.getDiscountPercentage());
		promocodeRepository.save(promocode);
		PromocodeDto response = promocodeMapper.entityToDto(promocode);
		return response;
	}

}
