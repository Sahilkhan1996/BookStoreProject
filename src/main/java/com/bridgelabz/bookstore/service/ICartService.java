package com.bridgelabz.bookstore.service;

import java.util.List;

import com.bridgelabz.bookstore.dto.CartDto;
import com.bridgelabz.bookstore.model.CartModel;

public interface ICartService {

	CartModel insertToCart(String token, CartDto cartdto);

	List<CartModel> multipleBooksinsertToCart(String token, List<CartDto> cartdto);

	List<CartModel> getAllCart(String token);

	CartModel getCartById(int id);

	public String deleteCartById(int cartId);

	CartModel updateBookById(int id, CartDto cartDto);

	String updateQuantity(int id, int qty);

	CartModel insertToCart(int userId, int bookId, int quantity);

	List<CartModel> getBooksById(int userid);

}
