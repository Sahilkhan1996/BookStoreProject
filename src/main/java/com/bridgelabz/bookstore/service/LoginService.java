package com.bridgelabz.bookstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstore.dto.LoginDto;
import com.bridgelabz.bookstore.model.LoginModel;
import com.bridgelabz.bookstore.model.RegisterModel;
import com.bridgelabz.bookstore.repository.LoginRepository;
import com.bridgelabz.bookstore.util.TokenUtil;
@Service
public class LoginService {
	
	@Autowired
	LoginRepository repo;
	@Autowired
	TokenUtil tokenUtil;
	
	public String login(LoginDto registerDto) {
		LoginModel loginModel=new LoginModel(registerDto);
		repo.save(loginModel);
		String token= tokenUtil.createToken(loginModel.getLoginId());
		 return token;
		
		
	}

}
