package com.example.Book_Shopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Book_Shopping.model.BookDto;
import com.example.Book_Shopping.service.BookService;

@RestController
@RequestMapping("/v1/api/books")
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@GetMapping
	public List<BookDto> getAllBooks(){
		return bookService.getAllBooks();
	}
	
	@GetMapping("/{id}")
	public BookDto getBookById(@PathVariable("id") Long id) {
		return bookService.getById(id);
	}
	
	@PostMapping
	public BookDto createBook(@RequestBody BookDto bookDto) {
		return bookService.createBook(bookDto);
	}
	
	@PutMapping("/{id}")
	public BookDto updateBook(@PathVariable("id") Long id,
			@RequestBody BookDto bookDto) {
		return bookService.updateBook(bookDto, id);
	}
	
	@GetMapping("/filter")
	public List<BookDto> findBooksByPriceRange(
			@RequestParam("minPrice") Double minPrice,
			@RequestParam("maxPrice") Double maxPrice){
		return bookService.findBooksByPriceRange(minPrice, maxPrice);
	}
	
	@GetMapping("/by-author")
	public List<BookDto> getBooksByAuthorName(@RequestParam String authorName) {
		return bookService.getBooksByAuthorName(authorName);
	}
	
}
