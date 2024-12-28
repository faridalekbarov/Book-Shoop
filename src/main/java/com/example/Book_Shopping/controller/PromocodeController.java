package com.example.Book_Shopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Book_Shopping.model.PromocodeDto;
import com.example.Book_Shopping.service.PromocodeService;

@RestController
@RequestMapping("/v1/api/promocodes")
public class PromocodeController {

	@Autowired
	private PromocodeService promocodeService;

	@GetMapping
	public List<PromocodeDto> getAllPromocodes() {
		return promocodeService.getAllPromocodes();
	}

	@GetMapping("/{id}")
	public PromocodeDto getPromocodeById(@PathVariable("id") Long id) {
		return promocodeService.getById(id);
	}

	@PostMapping
	public PromocodeDto createPromocode(@RequestBody PromocodeDto promocodeDto) {
		return promocodeService.createPromocode(promocodeDto);
	}

	@PutMapping("/{id}")
	public PromocodeDto updatePromocode(@PathVariable("id") Long id, @RequestBody PromocodeDto promocodeDto) {
		return promocodeService.updatePromocode(promocodeDto, id);
	}
}
