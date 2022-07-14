package com.bridgelabz.bookstoreproject.DTO;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@NoArgsConstructor
@ToString
public class BookDetailsDTO {

    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$",message = "Invalid name...!")
    private String bookName;
    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$",message = "Invalid name...!")
    private String authorName;
    @NotNull(message = "Book Description should not be null")
    private String bookDescription;
    @NotNull(message = "BookImg should not be null")
    private String bookImg;
    @NotNull(message = "Price should not be null")
    private int price;
    @NotNull(message = "Quantity should not be null")
    private int quantity;
    @NotNull(message = "Book names should not be null")
    private List<String> bookNames;
}
