package com.example.Book_Shopping.model;

import java.util.List;

public class OrderDto {
	private Long id;
	private Double totalPrice;
	private String status;
	private UserDto user;
	private CourierDto courier;
	private List<BookDto> books;
	private String promocode;
	private Double discount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public CourierDto getCourier() {
		return courier;
	}

	public void setCourier(CourierDto courier) {
		this.courier = courier;
	}

	public List<BookDto> getBooks() {
		return books;
	}

	public void setBooks(List<BookDto> bookDtos) {
		this.books = bookDtos;
	}

	public String getPromocode() {
		return promocode;
	}

	public void setPromocode(String promocode) {
		this.promocode = promocode;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

}
