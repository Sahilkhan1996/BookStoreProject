package com.bridgelabz.bookstore.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDto {

	private int quantity;
	private String address;
	private int userId;
	private int bookId;
	private boolean cancel;

}
