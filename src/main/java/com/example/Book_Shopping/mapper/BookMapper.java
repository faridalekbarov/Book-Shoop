package com.example.Book_Shopping.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.Book_Shopping.dao.entity.Book;
import com.example.Book_Shopping.model.BookDto;

@Component
public class BookMapper {

	public BookDto entityToDto(Book book) {
		BookDto bookDto = new BookDto();
		bookDto.setId(book.getId());
		bookDto.setAuthorName(book.getAuthorName());
		bookDto.setName(book.getName());
		bookDto.setPageCount(book.getPageCount());
		bookDto.setPrice(book.getPrice());
		return bookDto;
	}
	
	public List<BookDto> entityListToDtoList(List<Book> books){
		List<BookDto> bookDtos = new ArrayList<>();
		books.stream()
		.forEach(it -> {
			BookDto bookDto = entityToDto(it);
			bookDtos.add(bookDto);
		});
		
		return bookDtos;
	}
	
	public Book dtoToEntity(BookDto bookDto){
		Book book = new Book();
		book.setId(bookDto.getId());
		book.setAuthorName(bookDto.getAuthorName());
		book.setName(bookDto.getName());
		book.setPageCount(bookDto.getPageCount());
		book.setPrice(bookDto.getPrice());
		return book;
	}
	
}
