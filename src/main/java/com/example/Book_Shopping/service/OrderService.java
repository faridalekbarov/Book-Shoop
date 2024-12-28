package com.example.Book_Shopping.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Book_Shopping.dao.entity.Book;
import com.example.Book_Shopping.dao.entity.Courier;
import com.example.Book_Shopping.dao.entity.Order;
import com.example.Book_Shopping.dao.entity.Promocode;
import com.example.Book_Shopping.dao.entity.User;
import com.example.Book_Shopping.dao.repository.BookRepository;
import com.example.Book_Shopping.dao.repository.CourierRepository;
import com.example.Book_Shopping.dao.repository.OrderRepository;
import com.example.Book_Shopping.exception.BadRequestException;
import com.example.Book_Shopping.exception.NotFoundException;
import com.example.Book_Shopping.helper.UserHelper;
import com.example.Book_Shopping.mapper.OrderMapper;
import com.example.Book_Shopping.model.OrderCreateDto;
import com.example.Book_Shopping.model.OrderDto;
import com.example.Book_Shopping.security.JwtUtils;

@Service
public class OrderService {

	@Autowired
	private UserHelper userHelper;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CourierRepository courierRepository;

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private PromocodeService promocodeService;

	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private JwtUtils jwtUtils;

	public Order processOrder(Order order, String promocodeInput) {
		if (promocodeInput != null && !promocodeInput.isEmpty()) {
			Promocode promocode = promocodeService.findByCode(promocodeInput)
					.orElseThrow(() -> new NotFoundException("Invalid promocode"));
			promocodeService.assignPromocodeToOrder(promocode, order);
		}
		return order;
	}

	public List<OrderDto> getAllOrders() {
		String userName = jwtUtils.getAuthenticatedUserId();
		User user = userHelper.findUserByUserName(userName);
		List<Order> orders = orderRepository.findByUserId(user.getId());
		List<OrderDto> orderDtos = orderMapper.entityListToDtoList(orders);
		return orderDtos;
	}

	public OrderDto getUserOrderDetails(Long orderId) {
		String userName = jwtUtils.getAuthenticatedUserId();
		User user = userHelper.findUserByUserName(userName);
		Order order = orderRepository.findByUserIdAndId(user.getId(), orderId);
		if (order == null) {
			throw new NotFoundException("I don't have the id-li order");
		}
		OrderDto orderDto = orderMapper.entityToDto(order);
		return orderDto;
	}

	public OrderDto createOrder(OrderCreateDto orderCreateDto) {
		String userName = jwtUtils.getAuthenticatedUserId();
		User user = userHelper.findUserByUserName(userName);
		List<Book> books = bookRepository.findByIdIn(orderCreateDto.getBookIds());
		if (books.isEmpty()) {
			throw new NotFoundException("No books found for the provided IDs");
		}
		Order order = new Order();
		order.setUser(user);
		order.setBooks(books);
		order.setStatus("ORDERED");
		Double totalPrice = calculateTotalPrice(books);
		String promocodeInput = orderCreateDto.getPromocode();
		if (promocodeInput != null && !promocodeInput.isEmpty()) {
			Double discount = promocodeService.calculateDiscount(totalPrice, promocodeInput);
			order.setDiscount(discount);
			totalPrice = promocodeService.applyPromocodeDiscount(totalPrice, promocodeInput);
			order.setPromocode(promocodeInput);
		}
		order.setTotalPrice(totalPrice);
		orderRepository.save(order);
		return orderMapper.entityToDto(order);
	}

	private Double calculateTotalPrice(List<Book> books) {
		return books.stream().mapToDouble(Book::getPrice).sum();
	}

	public OrderDto assingOrderToCourier(Long orderId, Long courierId) {
		Order order = orderRepository.findById(orderId).orElseThrow(() -> {
			throw new NotFoundException("Order not found with given id");
		});

		Courier courier = courierRepository.findById(courierId).orElseThrow(() -> {
			throw new NotFoundException("Courier not found with given id");
		});

		order.setCourier(courier);
		order.setStatus("DONE");
		orderRepository.save(order);
		return orderMapper.entityToDto(order);
	}

	public OrderDto cancelOrder(Long orderId) {
		Order order = orderRepository.findById(orderId)
				.orElseThrow(() -> new NotFoundException("Order not found with given id"));
		if ("DONE".equals(order.getStatus()) || "CANCELED".equals(order.getStatus())) {
			throw new NotFoundException("Cannot cancel this order as it is already completed or canceled.");
		}
		if (order.getCourier() == null) {
			order.setStatus("CANCELED");
			orderRepository.save(order);
		} else {
			throw new NotFoundException("Cannot cancel the order as a courier has been assigned.");
		}
		return orderMapper.entityToDto(order);
	}

	public List<OrderDto> findOrdersByTotalPriceRange(Double minTotalPrice, Double maxTotalPrice) {
		if (minTotalPrice >= maxTotalPrice)
			throw new BadRequestException("Minimum total price must be less then maximum total price");
		List<Order> orders = orderRepository.findByTotalPriceBetween(minTotalPrice, maxTotalPrice);
		List<OrderDto> response = orderMapper.entityListToDtoList(orders);
		return response;
	}

}
