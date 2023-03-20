package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.CartDto;
import com.bridgelabz.bookstore.model.BookModel;
import com.bridgelabz.bookstore.model.CartModel;
import com.bridgelabz.bookstore.model.UserModel;
import com.bridgelabz.bookstore.repository.BookRepository;
import com.bridgelabz.bookstore.repository.CartRepository;
import com.bridgelabz.bookstore.repository.UserRepository;
import com.bridgelabz.bookstore.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService implements ICartService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    TokenUtil tokenUtil;



    @Override
    public CartModel insertToCart(int userId, int bookId, int quantity) {
        Optional<BookModel> book = bookRepository.findById(bookId);
        Optional<UserModel> user = userRepository.findById(userId);
        if (book.isPresent() || user.isPresent()) {
            CartModel newCart = new CartModel(quantity, book.get(), user.get());
            cartRepository.save(newCart);
            return newCart;
        }
        return null;
    }
    @Override
    public CartModel insertToCart(String token, CartDto cartdto) {

        System.out.println(token);
        int id = tokenUtil.decodeToken(token);

        Optional<BookModel> book = bookRepository.findById(cartdto.getBookId());
        Optional<UserModel> user = userRepository.findById(id);

        CartModel newCart = new CartModel(cartdto.getQuantity(), book.get(), user.get());
        cartRepository.save(newCart);
        return newCart;
    }

    @Override
    public List<CartModel> multipleBooksinsertToCart(String token, List<CartDto> cartdto) {
        System.out.println(token);
        int id = tokenUtil.decodeToken(token);
        Optional<UserModel> userModel = userRepository.findById(id);

//        Iterable<Integer> ids=cartdto.stream().map(i1->i1.getBookId()).collect(Collectors.toList());
//        List<BookModel> booksList=bookRepository.findAllById(ids);
        List<CartModel> listOfCardModel = new ArrayList<>();
        for (CartDto cartDto1 : cartdto) {
            Optional<BookModel> book = bookRepository.findById(cartDto1.getBookId());
            CartModel newCart = new CartModel(cartDto1.getQuantity(), book.get(), userModel.get());
            listOfCardModel.add(cartRepository.save(newCart));
        }
        return listOfCardModel;
    }

    @Override
    public List<CartModel> getAllCart(String token) {
        int id = tokenUtil.decodeToken(token);
        List<CartModel> cartModels = cartRepository.findCartItemsByUserId(id);
        return cartModels;
    }

    @Override
    public CartModel getCartById(int id) {
        CartModel cartModel = cartRepository.findById(id).get();
        return cartModel;
    }

    /*@Override
    public void deleteCart(String userToken, Long book_id) {
        long user_id = util.decodeToken(userToken);
        repository.deleteCartItemByUserId(user_id, book_id);
        // CartData cart = repository.findById(bookId).get();
        // repository.delete(cart);
        
    }
 */


    @Override
    public String deleteCartById(int cartId) {
        cartRepository.deleteById(cartId);
        return "Book removed from cart";
    }

    @Override
    public CartModel updateBookById(int id, CartDto cartDto) {
        Optional<CartModel> cartModel = cartRepository.findById(id);
        Optional<BookModel> book = bookRepository.findById(cartDto.getBookId());
        Optional<UserModel> user = userRepository.findById(cartDto.getUserId());
        if (cartModel != null) {
            CartModel updateCart = new CartModel(id, cartDto.getQuantity(), book.get(), user.get());
            cartRepository.save(updateCart);
            return updateCart;

        }

        return null;
    }

    @Override
    public String updateQuantity(int id, int qty) {
        CartModel cart = cartRepository.findById(id).get();
        if (cart != null) {
            cart.setQuantity(qty);
            cartRepository.save(cart);
            return "quantity modified";
        }

        return "cart not present";
    }

    @Override
    public List<CartModel> getBooksById(int userid) {
        List<CartModel> cartModel = cartRepository.findByUserId(userid);
        return cartModel;
    }
}
