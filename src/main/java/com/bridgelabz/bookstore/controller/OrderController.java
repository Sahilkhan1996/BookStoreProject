package com.bridgelabz.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.bookstore.dto.OrderDto;
import com.bridgelabz.bookstore.dto.ResponseDto;
import com.bridgelabz.bookstore.model.OrderModel;
import com.bridgelabz.bookstore.service.IOrderService;

@CrossOrigin(allowedHeaders = "*", origins = "*")
@RequestMapping("/order")
@RestController
public class OrderController {
	
	@Autowired
	IOrderService service;


    @GetMapping ("/test")
    ResponseEntity<String> testOrderController()
    {
        return new ResponseEntity<>("Hello from Order Controller",HttpStatus.OK);
    }
	
	
	//Api to insert order
	/*@PostMapping("/insertorder")
	ResponseEntity<ResponseDto> insertOrder(@RequestBody OrderDto orderDto)
	{
		OrderModel orderModel=service.insertOrder(orderDto);
		ResponseDto responseDto=new ResponseDto("Order placed successfully ",orderModel);
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
		
	}*/
	
	@PostMapping("/add/{token}")
	ResponseEntity<ResponseDto> insertOrder(@PathVariable String token )
	{
		OrderModel orderModel=service.insertOrder(token);
		ResponseDto responseDto=new ResponseDto("Order placed successfully ",orderModel);
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
		
	}
	
	//Api to get all orders
	@GetMapping("/getallorders")
	ResponseEntity<ResponseDto> getAllOrders()
	{
		List<OrderModel> orderModels=service.getAllOrders();
		ResponseDto responseDto=new ResponseDto("All orders are ",orderModels);
		return new ResponseEntity<>(responseDto,HttpStatus.OK);
	}
	
	@GetMapping("/getprocessorders")
	ResponseEntity<ResponseDto> getProcessOrders()
	{
		List<OrderModel> orderModels=service.getProcessOrders();
		ResponseDto responseDto=new ResponseDto("All orders are ",orderModels);
		return new ResponseEntity<>(responseDto,HttpStatus.OK);
	}
	
	@GetMapping("/getcompleteorders")
	ResponseEntity<ResponseDto> getCompleteOrders()
	{
		List<OrderModel> orderModels=service.getCompleteOrders();
		ResponseDto responseDto=new ResponseDto("All orders are ",orderModels);
		return new ResponseEntity<>(responseDto,HttpStatus.OK);
	}
	
	//Api to get all orders
		@GetMapping("/getcancelorders")
		ResponseEntity<ResponseDto> getCancelOrders()
		{
			List<OrderModel> orderModels=service.getCancelOrders();
			ResponseDto responseDto=new ResponseDto("All orders are ",orderModels);
			return new ResponseEntity<>(responseDto,HttpStatus.OK);
		}
	
	//Api to get order by order id
	@GetMapping("/orderid/{id}")
	ResponseEntity<ResponseDto> getOrderById(@PathVariable int id)
	{
		OrderModel orderModel=service.getOrderById(id);
		ResponseDto responseDto=new ResponseDto("Order fetched successfully ", orderModel);
		return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
	}
	
	//Api to delete order by order id
	@DeleteMapping("/orderid/{id}")
	ResponseEntity<ResponseDto> deleteOrderById(@PathVariable int id)
	{
		String orderModel=service.deleteOrderById(id);
		ResponseDto responseDto=new ResponseDto("Order deleted successfully ", orderModel);
		return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
		
	}
	
	//Api to update order by book id 
	@PutMapping("/orderid/{id}")
	ResponseEntity<ResponseDto> updateOrderById(@PathVariable int id,@RequestBody OrderDto orderDto)
	{
		String orderModel=service.updateOrderById(id,orderDto);
		ResponseDto responseDto=new ResponseDto("Record Updated successfully", orderModel);
		return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
	}
	
	//Api to cancel order by order id
	@PutMapping("/cancelorder/{id}")
	ResponseEntity<ResponseDto> cancelOrder(@PathVariable int id)
	{
		OrderModel orderModel=service.cancelOrder(id);
		ResponseDto responseDto=new ResponseDto("Order cancelled succesfully ...",orderModel);
		return new ResponseEntity<>(responseDto,HttpStatus.OK);
	}
}
