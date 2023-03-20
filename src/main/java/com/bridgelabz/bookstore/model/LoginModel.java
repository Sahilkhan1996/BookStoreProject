package com.bridgelabz.bookstore.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.bridgelabz.bookstore.dto.LoginDto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class LoginModel {

	
	@Id
	@GeneratedValue
	int loginId;
	String email;
	String password;
	
public LoginModel(LoginDto registerDto) {
	
	this.email=registerDto.getEmail();
	this.password=registerDto.getPassword();
		
	}
}
