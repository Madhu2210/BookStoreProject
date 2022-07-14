package com.bridgelabz.bookstoreproject.service;

import com.bridgelabz.bookstoreproject.DTO.CartDTO;
import com.bridgelabz.bookstoreproject.DTO.OrderDTO;
import com.bridgelabz.bookstoreproject.model.CartModel;
import com.bridgelabz.bookstoreproject.model.OrderModel;

import java.util.List;
import java.util.Optional;

public interface IOrderService {
    String getMessage();

    OrderModel addToOrder(OrderDTO orderDTO);

    Optional<OrderModel> getOrderById(int getOrderId);

    List<OrderModel> getListOfOrderDetails();

    void deleteBookFromOrder(int orderId);

    OrderModel updateOrder(int orderId, OrderDTO orderDTO);


    List<OrderModel> deleteAll();
}
