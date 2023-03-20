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
public class UserModel {
	
	
	@Id
	@GeneratedValue
	int userId;
	String firstName;
	String lastName;
	String email;
	String address;
	String dob;
	String password;
	
	public UserModel(UserDto userDto)
	{

		this.updateUserModel(userDto);
	}
	
	public void updateUserModel(UserDto userDto) {
		this.setFirstName(userDto.getFirstName());
		this.setLastName(userDto.getLastName());
		this.setEmail(userDto.getEmail());
		this.setAddress(userDto.getAddress());
		this.setDob(userDto.getDob());
		this.setPassword(userDto.getPassword());
	}

}
