package com.bridgelabz.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstore.dto.UserDto;
import com.bridgelabz.bookstore.model.RegisterModel;
import com.bridgelabz.bookstore.repository.RegisterRepository;
import com.bridgelabz.bookstore.util.TokenUtil;

@Service
public class RegisterService {
	@Autowired
	RegisterRepository repo;
	@Autowired
	TokenUtil tokenUtil;
	
		public String  register(UserDto registerDto) {
		RegisterModel userModel = new RegisterModel(registerDto);
		repo.save(userModel);
		String token=tokenUtil.createToken(userModel.getUserId());
		return token;
}
}
