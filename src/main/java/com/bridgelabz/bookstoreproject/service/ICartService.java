package com.bridgelabz.bookstoreproject.service;

import com.bridgelabz.bookstoreproject.DTO.CartDTO;
import com.bridgelabz.bookstoreproject.model.CartModel;

import java.util.List;
import java.util.Optional;

public interface ICartService {
    String getMessage();

    CartModel addToCart(CartDTO cartDTO);

    Optional<CartModel> getCartById(int getCartId);

    List<CartModel> getListOfCartDetails();

    void deleteBook(int cartId);

    List<CartModel> deleteAllFromCart();

    CartModel updateCart(int cartId, CartDTO cartDTO);
}
