package com.bridgelabz.bookstore.dto;


import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.sun.istack.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {
	
	@Pattern(regexp = "^[A-Z]{1}[a-z]{2,}", message = "Please enter valid first name")
	String firstName;
	@Pattern(regexp = "^[A-Z]{1}[a-z]{2,}", message = "Please enter valid last name")
	String lastName;
	@Email(message = "Please enter valid email")
	String email;
	@NotBlank(message = "please add address")
	String address;
	@NotBlank(message = "please enter dob")
	String dob;
	@NotNull
	String password;	

}
