package com.bridgelabz.bookstoreproject.model;

import com.bridgelabz.bookstoreproject.DTO.OrderDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@ToString

public class OrderModel {

    @Id
    @GeneratedValue
    private int orderId;
    private LocalDate date = LocalDate.now();
    private int price;
    private int quantity;
    private String address;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserModel user;
    @ManyToOne
    @JoinColumn(name = "book_details_book_id")
    private BookModel bookModel;
    private boolean cancel;


    public OrderModel(UserModel userModel, BookModel book, OrderDTO orderDTO) {
        this.user=userModel;
        this.bookModel=book;
        this.quantity=orderDTO.getQuantity();
        this.address=orderDTO.getAddress();
        this.price=orderDTO.getPrice();
        this.cancel=orderDTO.isCancel();
    }
}