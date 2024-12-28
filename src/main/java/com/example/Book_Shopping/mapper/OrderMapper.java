package com.example.Book_Shopping.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.Book_Shopping.dao.entity.Order;
import com.example.Book_Shopping.model.BookDto;
import com.example.Book_Shopping.model.CourierDto;
import com.example.Book_Shopping.model.OrderDto;
import com.example.Book_Shopping.model.UserDto;

@Component
public class OrderMapper {

	 public OrderDto entityToDto(Order order) {
	        OrderDto orderDto = new OrderDto();
	        orderDto.setId(order.getId());

	        if (order.getBooks() != null && !order.getBooks().isEmpty()) {
	            List<BookDto> bookDtos = order.getBooks().stream()
	                .map(book -> {
	                    BookDto bookDto = new BookDto();
	                    bookDto.setId(book.getId());
	                    bookDto.setPageCount(book.getPageCount());
	                    bookDto.setAuthorName(book.getAuthorName());
	                    bookDto.setPrice(book.getPrice());
	                    return bookDto;
	                })
	                .collect(Collectors.toList());
	            orderDto.setBooks(bookDtos);
	        } else {
	            orderDto.setBooks(new ArrayList<>());
	        }

	        if (order.getCourier() != null) {
	            CourierDto courierDto = new CourierDto();
	            courierDto.setId(order.getCourier().getId());
	            courierDto.setName(order.getCourier().getName());
	            orderDto.setCourier(courierDto);
	        }

	        orderDto.setStatus(order.getStatus());
	        orderDto.setPromocode(order.getPromocode());
	        orderDto.setTotalPrice(order.getTotalPrice());
	        orderDto.setDiscount(order.getDiscount());

	        UserDto userDto = new UserDto();
	        userDto.setEmail(order.getUser().getEmail());
	        userDto.setName(order.getUser().getName());
	        userDto.setUserName(order.getUser().getUserName());
	        orderDto.setUser(userDto);

	        return orderDto;
	    }

	    public List<OrderDto> entityListToDtoList(List<Order> orders) {
	        List<OrderDto> orderDtos = new ArrayList<>();
	        orders.forEach(order -> {
	            OrderDto orderDto = entityToDto(order);
	            orderDtos.add(orderDto);
	        });

	        return orderDtos;
	    }
	}
