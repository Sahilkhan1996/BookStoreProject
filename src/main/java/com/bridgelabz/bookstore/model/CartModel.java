package com.bridgelabz.bookstore.model;

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
public class CartModel {
	
	@Id
	@GeneratedValue
	private int cartId;	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserModel user;
	@ManyToOne
	@JoinColumn(name = "book_id")
	private BookModel book;
	private int quantity;
	
		
	
	public CartModel(int cartId, int quantity, BookModel book, UserModel user) {
        super();
        this.cartId = cartId;
        this.quantity = quantity;
        this.book = book;
        this.user = user;
        
    }

    public CartModel(int quantity, BookModel book, UserModel user) {
        super();
        this.quantity = quantity;
        this.book = book;
        this.user = user;
    }
    
    public CartModel(int quantity, BookModel book, LoginModel user) {
        super();
        this.quantity = quantity;
        this.book = book;
        //this.user = user;
    }

}
