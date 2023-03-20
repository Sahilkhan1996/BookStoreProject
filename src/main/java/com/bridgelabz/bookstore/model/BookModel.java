package com.bridgelabz.bookstore.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.bridgelabz.bookstore.dto.BookDto;


import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
public class BookModel {
	
	@Id
	@GeneratedValue
	private int bookId;
	private String bookName;
	private String autherName;
	private String bookDescription;
	private String bookImg;  
	private double price;
	private int quantity;
	
	
	public BookModel(BookDto bookDto)
	{
		this.updateBookModel(bookDto);
	}
	
	public void updateBookModel(BookDto bookDto) {
		this.setBookName(bookDto.getBookName());
		this.setAutherName(bookDto.getAutherName());
		this.setBookDescription(bookDto.getBookDescription());
		this.setBookImg(bookDto.getBookImg());
		this.setPrice(bookDto.getPrice());
		this.setQuantity(bookDto.getQuantity());
	}

}
