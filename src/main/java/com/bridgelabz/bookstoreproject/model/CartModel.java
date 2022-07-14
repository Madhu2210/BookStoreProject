package com.bridgelabz.bookstoreproject.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@ToString
@Table(name = "cart_model")
public class CartModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name="cart_id")
    private int cartId;

    @OneToOne
    @JoinColumn(name = "user_model_id")
    private UserModel userModel;

    @ManyToOne
    @JoinColumn(name = "book_model_book_id")
    private BookModel bookModel;

    private int quantity;


    public CartModel(UserModel userModel, BookModel book, int quantity) {
        this.userModel=userModel;
        this.bookModel=book;
        this.quantity=quantity;
    }
}
