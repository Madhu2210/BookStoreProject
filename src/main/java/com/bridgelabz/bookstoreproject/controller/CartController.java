package com.bridgelabz.bookstoreproject.controller;

import com.bridgelabz.bookstoreproject.DTO.CartDTO;
import com.bridgelabz.bookstoreproject.DTO.ResponseDTO;
import com.bridgelabz.bookstoreproject.model.CartModel;
import com.bridgelabz.bookstoreproject.service.IBookDetailsService;
import com.bridgelabz.bookstoreproject.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    ICartService cartService;

    @GetMapping("/hello")
    public String hello() {
        String msg = cartService.getMessage();
        return msg;
    }

    @PostMapping("/addToCart")
    public ResponseEntity<ResponseDTO> addToCart(@RequestBody CartDTO cartDTO) {
        CartModel cartModel = cartService.addToCart(cartDTO);
        ResponseDTO responseDTO = new ResponseDTO("Added to cart Successfully", cartModel);
        return new ResponseEntity<> (responseDTO, HttpStatus.OK);
    }

    @GetMapping("/cartById/{cartId}")
    public ResponseEntity<ResponseDTO> getCartDetails(@PathVariable int getCartId) {
        Optional<CartModel> cart = cartService.getCartById(getCartId);
        ResponseDTO response = new ResponseDTO("Cart details retrieved successfully by id:" +" " +getCartId,cart);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/cartDetails")
    public ResponseEntity<ResponseDTO> getAll() {
        List<CartModel> cartDetailsList = cartService.getListOfCartDetails();
        ResponseDTO response = new ResponseDTO("Retrieved cart detailed list.x", cartDetailsList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/deleteById/{cartId}")
    public ResponseEntity<ResponseDTO> deleteBook(@PathVariable int cartId) {
        cartService.deleteBook(cartId);
        ResponseDTO response = new ResponseDTO("Book removed from cart by Id",cartId );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping ("/deleteAll")
    public ResponseEntity<ResponseDTO> deleteAll() {
        List<CartModel> removeAll = cartService.deleteAllFromCart();
        ResponseDTO response = new ResponseDTO("Cart empty", removeAll);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/updateCart/{cartId}")
    public ResponseEntity<ResponseDTO> updateCart(@PathVariable int cartId,
                                                          @RequestParam CartDTO cartDTO) {
        CartModel updateCart = cartService.updateCart(cartId, cartDTO);
        ResponseDTO response = new ResponseDTO("Cart updated successfully", updateCart);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
