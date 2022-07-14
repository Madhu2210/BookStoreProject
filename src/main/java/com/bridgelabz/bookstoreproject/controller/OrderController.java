package com.bridgelabz.bookstoreproject.controller;

import com.bridgelabz.bookstoreproject.DTO.CartDTO;
import com.bridgelabz.bookstoreproject.DTO.OrderDTO;
import com.bridgelabz.bookstoreproject.DTO.ResponseDTO;
import com.bridgelabz.bookstoreproject.model.CartModel;
import com.bridgelabz.bookstoreproject.model.OrderModel;
import com.bridgelabz.bookstoreproject.service.ICartService;
import com.bridgelabz.bookstoreproject.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    IOrderService orderService;

    @GetMapping("/hello")
    public String hello() {
        String msg = orderService.getMessage();
        return msg;
    }

    @PostMapping("/placeOrder/{bookId}")
    public ResponseEntity<ResponseDTO> addToOrder(@RequestBody OrderDTO orderDTO) {
        OrderModel orderModel = orderService.addToOrder(orderDTO);
        ResponseDTO responseDTO = new ResponseDTO("Order Placed Successfully", orderModel);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/getOrderDetails/{getOrderId}")
    public ResponseEntity<ResponseDTO> getOrderDetails(@PathVariable int getOrderId) {
        Optional<OrderModel> orderModel = orderService.getOrderById(getOrderId);
        ResponseDTO response = new ResponseDTO("Order details retrieved successfully for id:" + " " + getOrderId,orderModel);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<ResponseDTO> getAll() {
        List<OrderModel> orderDetailsList = orderService.getListOfOrderDetails();
        ResponseDTO response = new ResponseDTO("Retrieved List of order Details Successfully", orderDetailsList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/deleteById/{orderId}")
    public ResponseEntity<ResponseDTO> deleteBook(@PathVariable int orderId) {
        orderService.deleteBookFromOrder(orderId);
        ResponseDTO response = new ResponseDTO("Book removed from order by Id",orderId );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/removeAll")
    public ResponseEntity<ResponseDTO> deleteAll() {
        List<OrderModel> removeAll = orderService.deleteAll();
        ResponseDTO response = new ResponseDTO(" is Empty", removeAll);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/updateOrder/{orderId}")
    public ResponseEntity<ResponseDTO> updateUser(@PathVariable int orderId,
                                                  @Valid @RequestBody OrderDTO orderDTO) {
        OrderModel newOrder = orderService.updateOrder(orderId, orderDTO);
        ResponseDTO response = new ResponseDTO("User updated successfully",newOrder);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
