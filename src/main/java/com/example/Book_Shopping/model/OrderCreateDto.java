package com.example.Book_Shopping.model;

import java.util.List;

public class OrderCreateDto {
	private String promocode;
	private List<Long> bookIds;

	public List<Long> getBookIds() {
		return bookIds;
	}

	public void setBookIds(List<Long> bookIds) {
		this.bookIds = bookIds;
	}

	public String getPromocode() {
		return promocode;
	}

	public void setPromocode(String promocode) {
		this.promocode = promocode;
	}
}
