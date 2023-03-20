package com.bridgelabz.bookstore.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookDto {
	@NotBlank
	String bookName;
	@NotBlank
	String autherName;
	@NotBlank
	String bookDescription;
	@NotBlank
	String bookImg;
	@NotBlank
	double price;
	@NotBlank
	int quantity;

}
