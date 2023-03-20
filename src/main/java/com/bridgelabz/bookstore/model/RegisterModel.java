package com.bridgelabz.bookstore.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.bridgelabz.bookstore.dto.UserDto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class RegisterModel {

	@Id
	@GeneratedValue
	int userId;
	String email;
	String password;
	String firstName;
	String lastName;
	String address;
	
	public RegisterModel(UserDto registerDto)
	{
		this.email=registerDto.getEmail();
		this.password=registerDto.getPassword();
		this.firstName=registerDto.getFirstName();
		this.lastName=registerDto.getLastName();
		this.address=registerDto.getAddress();
	}
	}
