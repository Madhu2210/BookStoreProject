package com.bridgelabz.bookstoreproject.service;

import com.bridgelabz.bookstoreproject.DTO.BookDetailsDTO;
import com.bridgelabz.bookstoreproject.model.BookModel;

import java.util.List;
import java.util.Optional;

public interface IBookDetailsService  {
    public String getMessage();

    BookModel addBookDetails(BookDetailsDTO bookDetailsDTO);

    List<BookModel> getListOfBooks();

    Optional<BookModel> getBookById(int getBookId);

    void deleteBook(int bookId);

    BookModel updateBookById(int getBookId, BookDetailsDTO bookDetailsDTO);

    BookModel updateBookQuantity(int getBookId, int quantity);

    List<BookModel> getListOfBooksInSortedDsc();

    List<BookModel> getListOfBooksInSortedAce();

    List<BookModel> getBookByName(String bookName);
}
