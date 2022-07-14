package com.bridgelabz.bookstoreproject.service;

import com.bridgelabz.bookstoreproject.DTO.CartDTO;
import com.bridgelabz.bookstoreproject.exception.UserException;
import com.bridgelabz.bookstoreproject.model.BookModel;
import com.bridgelabz.bookstoreproject.model.CartModel;
import com.bridgelabz.bookstoreproject.model.UserModel;
import com.bridgelabz.bookstoreproject.repository.IBookDetailsRepository;
import com.bridgelabz.bookstoreproject.repository.ICartRepository;
import com.bridgelabz.bookstoreproject.repository.IUserRepository;
import com.bridgelabz.bookstoreproject.util.EmailSenderService;
import com.bridgelabz.bookstoreproject.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService implements ICartService {

    @Autowired
    ICartRepository cartRepo;

    @Autowired
    TokenUtil tokenutil;

    @Autowired
    EmailSenderService sender;

    @Autowired
    IBookDetailsRepository bookRepo;

    @Autowired
    IUserRepository userRepo;

    @Override
    public String getMessage() {

        return "Hello welcome to Book Store";
    }

    @Override
    public CartModel addToCart(CartDTO cartDTO) {
        Optional<UserModel> isUserPresent = userRepo.findById(cartDTO.getUserId());
        if (isUserPresent.isPresent()) {
            BookModel book = bookRepo.findById(cartDTO.getBookId()).orElseThrow();
            CartModel cart = new CartModel(isUserPresent.get(), book, cartDTO.getQuantity());
            return cartRepo.save(cart);
        } else throw new UserException("Invalid User");
    }

    @Override
    public Optional<CartModel> getCartById(int getCartId) {
        Optional<CartModel> cartModel = cartRepo.findById(getCartId);
        if (cartModel.isPresent()) {
            return cartModel;
        } else {
            throw new UserException("Invalid cart Id");
        }
    }

    @Override
    public List<CartModel> getListOfCartDetails() {
        List<CartModel> cart = cartRepo.findAll();
        return cart;
    }

    @Override
    public void deleteBook(int cartId) {

        cartRepo.deleteById(cartId);
    }

    @Override
    public List<CartModel> deleteAllFromCart() {
        cartRepo.deleteAll();
        return null;
    }

    @Override
    public CartModel updateCart(int cartId, CartDTO cartDTO) {
        Optional<CartModel> cartDetails = cartRepo.findById(cartId);
            cartDetails.get().setQuantity(cartDTO.getQuantity());
            cartRepo.save(cartDetails.get());
            return cartDetails.get();
        }

}
