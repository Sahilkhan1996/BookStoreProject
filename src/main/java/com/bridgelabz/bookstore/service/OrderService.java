package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.OrderDto;
import com.bridgelabz.bookstore.model.BookModel;
import com.bridgelabz.bookstore.model.CartModel;
import com.bridgelabz.bookstore.model.OrderModel;
import com.bridgelabz.bookstore.model.UserModel;
import com.bridgelabz.bookstore.repository.BookRepository;
import com.bridgelabz.bookstore.repository.CartRepository;
import com.bridgelabz.bookstore.repository.OrderRepository;
import com.bridgelabz.bookstore.repository.UserRepository;
import com.bridgelabz.bookstore.util.EmailSenderService;
import com.bridgelabz.bookstore.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    EmailSenderService mail;
    @Autowired
    TokenUtil tokenUtil;
    @Autowired
    CartRepository cartRepository;

    @Override
	/*public OrderModel insertOrder(OrderDto orderDto) {
		Optional<UserModel> user=userRepository.findById(orderDto.getUserId());
		Optional<BookModel> book=bookRepository.findById(orderDto.getBookId());
		
		if(user!=null && book!=null)
		{
			OrderModel orderModel=new OrderModel(book.get().getPrice(),orderDto.getQuantity(),orderDto.getAddress(),user.get(),book.get(),orderDto.isCancel());
			orderRepository.save(orderModel);
			mail.sendEmail("iamswapnilengg@gmail.com", "order placed", user.get().getFirstName() +" placed order for " +book.get().getBookName()+ " on " +orderModel.getDate());
			return orderModel;
		}
		
		throw new BookStoreException("User or book not present") ;
	}*/

    public OrderModel insertOrder(String token) {
        int id = tokenUtil.decodeToken(token);
        UserModel userModel = userRepository.findById(id).get();

        List<CartModel> cartModel = cartRepository.byUserId(id);
        OrderModel model = new OrderModel();
        for (int i = 0; i < cartModel.size(); i++) {
            OrderModel orderModel = new OrderModel();
            orderModel.setAddress(userModel.getAddress());
            orderModel.setBook(cartModel.get(i).getBook());
            orderModel.setCancel(false);
            orderModel.setDate(LocalDate.now());
            orderModel.setPrice(cartModel.get(i).getBook().getPrice());
            orderModel.setTotalPrice(cartModel.get(i).getQuantity() * cartModel.get(i).getBook().getPrice());
            orderModel.setQuantity(cartModel.get(i).getQuantity());
            orderModel.setUser(cartModel.get(i).getUser());
            orderRepository.save(orderModel);
            model = orderModel;
            cartRepository.deleteById(cartModel.get(i).getCartId());
        }
       return model;
    }


    @Override
    public List<OrderModel> getAllOrders() {
        LocalDate currentDate = LocalDate.now();
        List<OrderModel> orderModels = orderRepository.findAll();
        List<OrderModel> oModels = new ArrayList<>();
        for (OrderModel omdModel : orderModels) {
            LocalDate date = omdModel.getDate();

            long differnce = ChronoUnit.DAYS.between(date, currentDate);

            if (!omdModel.isCancel() && (differnce >= 1 && differnce <= 3)) {

                oModels.add(omdModel);
            }


        }
        return oModels;
    }

    @Override
    public List<OrderModel> getProcessOrders() {
        LocalDate currentDate = LocalDate.now();
        List<OrderModel> orderModels = orderRepository.findAll();
        List<OrderModel> oModels = new ArrayList<>();
        for (OrderModel omdModel : orderModels) {
            LocalDate date = omdModel.getDate();

            long differnce = ChronoUnit.DAYS.between(date, currentDate);

            if (!omdModel.isCancel() && differnce < 1) {

                oModels.add(omdModel);
            }


        }
        return oModels;
    }

    @Override
    public List<OrderModel> getCompleteOrders() {
        LocalDate currentDate = LocalDate.now();
        List<OrderModel> orderModels = orderRepository.findAll();
        List<OrderModel> oModels = new ArrayList<>();
        for (OrderModel omdModel : orderModels) {
            LocalDate date = omdModel.getDate();

            long differnce = ChronoUnit.DAYS.between(date, currentDate);

            if (!omdModel.isCancel() && differnce > 3) {

                oModels.add(omdModel);
            }


        }
        return oModels;
    }

    @Override
    public List<OrderModel> getCancelOrders() {

        List<OrderModel> orderModels = orderRepository.findAll();
        List<OrderModel> oModels = new ArrayList<>();
        for (OrderModel omdModel : orderModels) {
            if (omdModel.isCancel()) {

                oModels.add(omdModel);
            }


        }
        return oModels;
    }

    @Override
    public OrderModel getOrderById(int id) {
        OrderModel orderModel = orderRepository.findById(id).get();
        return orderModel;
    }

    @Override
    public String deleteOrderById(int id) {

        OrderModel orderModel = orderRepository.findById(id).get();
        if (orderModel != null) {
            orderRepository.deleteById(id);
            return "Order deleted";
        }

        return "Invalid order id";
    }

    @Override
    public String updateOrderById(int id, OrderDto orderDto) {
        Optional<OrderModel> order = orderRepository.findById(id);
        Optional<UserModel> user = userRepository.findById(orderDto.getUserId());
        Optional<BookModel> book = bookRepository.findById(orderDto.getBookId());

        if (order != null) {
            OrderModel orderModel = new OrderModel(id, book.get().getPrice(), orderDto.getQuantity(), orderDto.getAddress(), user.get(), book.get(), orderDto.isCancel());
            orderRepository.save(orderModel);
            return "updated";
        }

        return null;
    }

    @Override
    public OrderModel cancelOrder(int id) {
        Optional<OrderModel> orderModel = orderRepository.findById(id);
        if (orderModel != null) {
            OrderModel newModel = new OrderModel(orderModel.get().getPrice(), orderModel.get().getQuantity(), orderModel.get().getAddress(), orderModel.get().getUser(), orderModel.get().getBook(), true);
            orderRepository.save(newModel);
            orderRepository.deleteById(id);
            return newModel;
        }
        return null;
    }

}
