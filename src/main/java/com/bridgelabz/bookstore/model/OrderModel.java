package com.bridgelabz.bookstore.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class OrderModel {
	@Id
	@GeneratedValue
	private int orderId;
	private LocalDate date= LocalDate.now();
	private double price;
	private int quantity;
	private String address;
	@ManyToOne
	@JoinColumn(name = "userId")
	private UserModel user;
	@ManyToOne
	@JoinColumn(name = "bookId")
	private BookModel book;
	private boolean cancel;
	private double totalPrice;
	
	public OrderModel(int id, double price,int quantity, String address,UserModel user, BookModel book,  Boolean cancel) {
        this.orderId=id;
		this.price=price;
        this.quantity=quantity;
        this.address=address;
        this.book = book;
        this.user=user;
        this.cancel = cancel;
        
    }
	
	public OrderModel(double price,int quantity,String address,UserModel user,BookModel book,boolean cancel)
	{
		this.price=price;
		this.quantity=quantity;
		this.address=address;
		this.user=user;
		this.book=book;
		this.cancel=cancel;
	}
	
	

}
