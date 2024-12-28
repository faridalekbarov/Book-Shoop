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

import com.example.Book_Shopping.model.CourierDto;
import com.example.Book_Shopping.service.CourierService;

@RestController
@RequestMapping("/v1/api/couriers")
public class CourierController {
	
	@Autowired
	private CourierService courierService;
	
	@GetMapping
	public List<CourierDto> getAllCouriers() {
		return courierService.getAllCouriers();
		}
	
	@PostMapping
	public CourierDto createCourier(@RequestBody CourierDto courierDto) {
		return courierService.createCourier(courierDto);
	}
	
	@GetMapping("/{id}")
	public CourierDto getCourierById(@PathVariable("id") Long id) {
		return courierService.getById(id);
	}
	
	@PutMapping("/{id}")
	public CourierDto updateCourier(@PathVariable("id") Long id, @RequestBody CourierDto courierDto) {
		return courierService.updateCourier(courierDto, id);
	}
}
