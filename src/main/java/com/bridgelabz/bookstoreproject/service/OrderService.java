package com.bridgelabz.bookstoreproject.service;

import com.bridgelabz.bookstoreproject.DTO.CartDTO;
import com.bridgelabz.bookstoreproject.DTO.OrderDTO;
import com.bridgelabz.bookstoreproject.exception.UserException;
import com.bridgelabz.bookstoreproject.model.BookModel;
import com.bridgelabz.bookstoreproject.model.CartModel;
import com.bridgelabz.bookstoreproject.model.OrderModel;
import com.bridgelabz.bookstoreproject.model.UserModel;
import com.bridgelabz.bookstoreproject.repository.IBookDetailsRepository;
import com.bridgelabz.bookstoreproject.repository.ICartRepository;
import com.bridgelabz.bookstoreproject.repository.IOrderRepository;
import com.bridgelabz.bookstoreproject.repository.IUserRepository;
import com.bridgelabz.bookstoreproject.util.EmailSenderService;
import com.bridgelabz.bookstoreproject.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService {

    @Autowired
    EmailSenderService sender;
    @Autowired
    IBookDetailsRepository bookRepo;
    @Autowired
    IUserRepository userRepo;

    @Autowired
    IOrderRepository orderRepo;

    @Override
    public String getMessage() {

        return "Hello welcome to Book Store";
    }

    @Override
    public OrderModel addToOrder(OrderDTO orderDTO) {
        Optional<UserModel> isUserPresent = userRepo.findById(orderDTO.getUserId());
        if (isUserPresent.isPresent()) {
            BookModel book = bookRepo.findById(orderDTO.getBookId()).orElseThrow();
            OrderModel orderModel = new OrderModel(isUserPresent.get(), book, orderDTO);
            sender.sendEmail(isUserPresent.get().getEmail(),"Test Mail",
                    "Dear "+isUserPresent.get().getFirstName()+
                            "Your Order has been placed Successfully and the details are: "+"\n" + book.toString());
            return orderRepo.save(orderModel);
        } else throw new UserException("Invalid User");
    }

    @Override
    public Optional<OrderModel> getOrderById(int getOrderId) {
        Optional<OrderModel> orderModel = orderRepo.findById(getOrderId);
        if (orderModel.isPresent()) {
            return orderModel;
        } else {
            throw new UserException("Order Id not found");
        }
    }

    @Override
    public List<OrderModel> getListOfOrderDetails() {
        List<OrderModel> orderDetailsList = orderRepo.findAll();
        return orderDetailsList;
    }

    @Override
    public void deleteBookFromOrder(int orderId) {

        orderRepo.deleteById(orderId);
    }

    @Override
    public OrderModel updateOrder(int orderId, OrderDTO orderDTO) {
        Optional<OrderModel> order = orderRepo.findById(orderId);
            order.get().setQuantity(orderDTO.getQuantity());
            order.get().setAddress(orderDTO.getAddress());
            order.get().setPrice(orderDTO.getPrice());
            order.get().setCancel(orderDTO.isCancel());
            orderRepo.save(order.get());
            return order.get();
        }

    @Override
    public List<OrderModel> deleteAll() {
        orderRepo.deleteAll();
        return null;
    }

}
