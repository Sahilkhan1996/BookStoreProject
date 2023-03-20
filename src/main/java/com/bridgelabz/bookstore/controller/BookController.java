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

import com.bridgelabz.bookstore.dto.BookDto;
import com.bridgelabz.bookstore.dto.ResponseDto;
import com.bridgelabz.bookstore.model.BookModel;
import com.bridgelabz.bookstore.service.IBookService;
@CrossOrigin(allowedHeaders = "*", origins = "*")
@RequestMapping("/book")
@RestController 
public class BookController {
	
	@Autowired
	IBookService service;

	@GetMapping("/test")
	public String testHello(){
		return "Hi from Book store program";
	}
	
	//Api to add book
	@PostMapping("/insertbook")
	ResponseEntity<ResponseDto> insertBook( @RequestBody BookDto bookDto)
	{
		String token=service.insertBook(bookDto);
		ResponseDto responseDto=new ResponseDto("Book inserted successfully ",token);
		return new ResponseEntity<>(responseDto,HttpStatus.OK);
	}
	
	//Api to get all books
	@GetMapping("/getallbook")
	ResponseEntity<ResponseDto> getAllBooks()
	{
		List<BookModel> users=service.getAllBooks();
		ResponseDto responseDto=new ResponseDto("All books are ",users);
		return new ResponseEntity<>(responseDto,HttpStatus.OK);
	}
	
	//Api to get book by book id
	@GetMapping("/get/{id}")
	ResponseEntity<ResponseDto> getBookById(@PathVariable int id)
	{
		BookModel bookModel=service.getBookById(id);
		ResponseDto responseDto=new ResponseDto("Required book is :",bookModel);
		return new ResponseEntity<>(responseDto,HttpStatus.OK);
	}
	
	//Api to get book by book name
	@GetMapping("/bookname/{bname}")
	ResponseEntity<ResponseDto> getBookByName(@PathVariable String bname)
	{
		BookModel bookModel=service.findBookByName(bname);
		ResponseDto responseDto=new ResponseDto("Required book is :",bookModel);
		return new ResponseEntity<>(responseDto,HttpStatus.OK);
		
	}
	
	//Api to add update book by book id
	@PutMapping("/bookupdate/{id}")
	ResponseEntity<ResponseDto> updateBookById(@PathVariable int id,@RequestBody BookDto bookDto)
	{
		BookModel bookModel=service.updateBookById(id,bookDto);
		ResponseDto responseDto=new ResponseDto("Book updated successfully  ", bookModel);
		return new ResponseEntity<>(responseDto,HttpStatus.OK);
	}
	
	//Api to delete book by token
	@DeleteMapping("/booktoken/{token}")
	ResponseEntity<ResponseDto> deleteBookByToken(@PathVariable String token)
	{
		String bookModel=service.deleteBookByToken(token);
		ResponseDto responseDto=new ResponseDto("User delete status :",bookModel);
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}
	//Api to find book by book id and modify quantity
	@PutMapping("/bookid/{bid}/qty/{qty}")
	ResponseEntity<ResponseDto> updateQuantityById(@PathVariable int bid, @PathVariable int qty)
	{
		String bookModel=service.updateQuantityByID(bid,qty);
		ResponseDto responseDto=new ResponseDto("Update quantity status", bookModel);
		return new ResponseEntity<>(responseDto,HttpStatus.OK);
	}
	
	//Api to find book by book name and modify quantity
	@PutMapping("/bookname/{bname}/qty/{qty}")
	ResponseEntity<ResponseDto> updateQuantity(@PathVariable String bname, @PathVariable int qty)
	{
		String bookModel=service.updateQuantity(bname,qty);
		ResponseDto responseDto=new ResponseDto("Update quantity status", bookModel);
		return new ResponseEntity<>(responseDto,HttpStatus.OK);
	}

	//Api to find book by book name and modify price
	@PutMapping("/bookid/{bid}/price/{price}")
	ResponseEntity<ResponseDto> updatePrice(@PathVariable int bid, @PathVariable Double price)
	{
		String bookModel=service.updatePrice(bid,price);
		ResponseDto responseDto=new ResponseDto("Update price status", bookModel);
		return new ResponseEntity<>(responseDto,HttpStatus.OK);

	}
//
//	//Api to arrange book in ascending order according to book name
//	@GetMapping("/bookascending")
//	ResponseEntity<ResponseDto> getBookInAscending()
//	{
//		List<BookModel> bookModel=service.getBookInAscending();
//		ResponseDto responseDto=new ResponseDto("Books in ascending orders are :",bookModel);
//		return new ResponseEntity<>(responseDto,HttpStatus.OK);
//	}
//
//	//Api to arrange book in descending order according to book name
//	@GetMapping("/bookdescending")
//	ResponseEntity<ResponseDto> getBookInDescending()
//	{
//		List<BookModel> bookModel=service.getBookInDescending();
//		ResponseDto responseDto=new ResponseDto("Books in descending orders are :",bookModel);
//		return new ResponseEntity<>(responseDto,HttpStatus.OK);
//	}


}
