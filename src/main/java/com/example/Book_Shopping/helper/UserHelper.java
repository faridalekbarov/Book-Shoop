package com.example.Book_Shopping.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.Book_Shopping.dao.entity.User;
import com.example.Book_Shopping.dao.repository.UserRepository;
import com.example.Book_Shopping.exception.NotFoundException;

@Component
public class UserHelper {

	@Autowired
	private UserRepository userRepository;

	public User findUserByUserName(String userName) {
		User user = userRepository.findByUserName(userName)
				.orElseThrow(() ->{
					throw new NotFoundException("User not found with given id");
				});
		return user;
	}


}
