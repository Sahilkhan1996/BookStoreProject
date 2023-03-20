package com.bridgelabz.bookstore.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

 @Data 
 @NoArgsConstructor
 public class CartDto {
	
	private int userId;
	private int bookId;
	private int quantity;

}
