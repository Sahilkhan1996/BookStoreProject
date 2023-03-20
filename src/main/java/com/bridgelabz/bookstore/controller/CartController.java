package com.bridgelabz.bookstore.controller;

import com.bridgelabz.bookstore.dto.CartDto;
import com.bridgelabz.bookstore.dto.ResponseDto;
import com.bridgelabz.bookstore.model.CartModel;
import com.bridgelabz.bookstore.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(allowedHeaders = "*", origins = "*")
@RequestMapping("/cart")
@RestController
public class CartController {

    @Autowired
    ICartService service;

    //Api to check cart controller
    @GetMapping("/test")
    public ResponseEntity<String> testHello() {
        return new ResponseEntity<>("Hello from Cart Controller", HttpStatus.OK);
    }

    //Api to add to cart

    @PostMapping("/insertcart/{userId}/{bookId}/{quantity}")
    public ResponseEntity<ResponseDto> insertToCard(@PathVariable int userId, @PathVariable int bookId, @PathVariable int quantity) {

        CartModel newCart = service.insertToCart(userId, bookId, quantity);
        ResponseDto responseDTO = new ResponseDto("book added successfully !", newCart);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }


    //Api to add to cart
    @PostMapping("/add/{token}")
    public ResponseEntity<ResponseDto> insertToCart(@PathVariable String token, @RequestBody CartDto cartdto) {
        CartModel newCart = service.insertToCart(token, cartdto);
        ResponseDto responseDTO = new ResponseDto("book added successfully !", newCart);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //Api to add to cart with number of books
    @PostMapping("/addBooks/{token}")
    public ResponseEntity<ResponseDto> multipleBooksInsertToCart(@PathVariable String token, @RequestBody List<CartDto> cartdto) {
        List<CartModel> newCart = service.multipleBooksinsertToCart(token, cartdto);
        ResponseDto responseDTO = new ResponseDto("book added successfully !", newCart);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }


    //Api to add get all carts with token
    @GetMapping("/all/{token}")
    ResponseEntity<ResponseDto> getAllCart(@PathVariable String token) {
        List<CartModel> cart = service.getAllCart(token);
        ResponseDto responseDto = new ResponseDto("All carts are ", cart);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    //Api to get cart by id
    @GetMapping("/cartid/{id}")
    ResponseEntity<ResponseDto> getBookById(@PathVariable int id) {
        CartModel cartModel = service.getCartById(id);
        ResponseDto responseDto = new ResponseDto("Required book is :", cartModel);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    //Api to get carts by user id
    @GetMapping("/userid/{userid}")
    ResponseEntity<ResponseDto> getBookDataById(@PathVariable int userid) {
        List<CartModel> cartModel = service.getBooksById(userid);
        ResponseDto responseDto = new ResponseDto("Required book is :", cartModel);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }


    //Api to delete cart by id
    @DeleteMapping("/remove/{cartId}")
    ResponseEntity<ResponseDto> deleteBookByToken(@PathVariable int cartId) {
        String cart = service.deleteCartById(cartId);
        ResponseDto responseDto = new ResponseDto("book delete status :", cart);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    //Api to update cart by id
    @PutMapping("/cartupdate/{id}")
    ResponseEntity<ResponseDto> updateBookById(@PathVariable int id, @RequestBody CartDto cartDto) {
        CartModel cartModel = service.updateBookById(id, cartDto);
        ResponseDto responseDto = new ResponseDto("cart updated successfully  ", cartModel);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    //Api to find cart by cart id and modify quantity
    @PutMapping("/cartid/{id}/qty/{qty}")
    ResponseEntity<ResponseDto> updateQuantity(@PathVariable int id, @PathVariable int qty) {
        String cartModel = service.updateQuantity(id, qty);
        ResponseDto responseDto = new ResponseDto("Update quantity status", cartModel);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);

    }
}
