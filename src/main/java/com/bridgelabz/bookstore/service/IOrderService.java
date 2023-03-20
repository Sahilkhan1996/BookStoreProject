package com.bridgelabz.bookstore.service;

import java.util.List;

import com.bridgelabz.bookstore.dto.OrderDto;
import com.bridgelabz.bookstore.model.OrderModel;
import com.bridgelabz.bookstore.model.UserModel;

public interface IOrderService {

	//OrderModel insertOrder(OrderDto orderDto);
	OrderModel insertOrder(String token);

	List<OrderModel> getAllOrders();

	OrderModel getOrderById(int id);

	String deleteOrderById(int id);

	String updateOrderById(int id, OrderDto orderDto);

	OrderModel cancelOrder(int id);

	List<OrderModel> getCancelOrders();
	List<OrderModel> getProcessOrders();
	List<OrderModel> getCompleteOrders();

}
