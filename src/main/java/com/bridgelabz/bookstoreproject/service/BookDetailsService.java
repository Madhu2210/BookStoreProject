package com.bridgelabz.bookstoreproject.service;

import com.bridgelabz.bookstoreproject.DTO.BookDetailsDTO;
import com.bridgelabz.bookstoreproject.exception.UserException;
import com.bridgelabz.bookstoreproject.model.BookModel;
import com.bridgelabz.bookstoreproject.repository.IBookDetailsRepository;
import com.bridgelabz.bookstoreproject.util.EmailSenderService;
import com.bridgelabz.bookstoreproject.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookDetailsService implements IBookDetailsService {

    @Autowired
    IBookDetailsRepository repo;

    @Autowired
    TokenUtil tokenutil;

    @Autowired
    EmailSenderService sender;

    @Override
    public String getMessage() {

        return "Hello welcome to Book Store";
    }

    @Override
    public BookModel addBookDetails(BookDetailsDTO bookDetailsDTO) {
        BookModel bookModel = new BookModel(bookDetailsDTO);
        repo.save(bookModel);
        sender.sendEmail("madhu.kahar2210@gmail.com", "Test Mail",
                "Book Added Successfully"+bookModel.toString());
        return bookModel;
    }

    @Override
    public List<BookModel> getListOfBooks() {
        List<BookModel> books = repo.findAll();
        return books;
    }

    @Override
    public Optional<BookModel> getBookById(int getBookId) {
        Optional<BookModel> book = repo.findById(getBookId);
        if (book.isPresent()) {
            return book;
        } else {
            throw new UserException("Book not found");
        }
    }

    @Override
    public void deleteBook(int bookId) {
        Optional<BookModel> bookModel = repo.findById(bookId);
        repo.deleteById(bookId);
        sender.sendEmail("madhu.kahar2210@gmail.com", "Test mail-BookStore",
                "Book deleted Successfully\" + bookDetails.toString());");
    }

    @Override
    public BookModel updateBookById(int getId, BookDetailsDTO bookDetailsDTO) {
        Optional<BookModel> newBook = repo.findById(getId);
        if(newBook.isPresent()){
            newBook.get().setBookName(bookDetailsDTO.getBookName());
            newBook.get().setAuthorName(bookDetailsDTO.getAuthorName());
            newBook.get().setBookDescription(bookDetailsDTO.getBookDescription());
            newBook.get().setBookImg(bookDetailsDTO.getBookImg());
            newBook.get().setPrice(bookDetailsDTO.getPrice());
            repo.save(newBook.get());
            sender.sendEmail("madhu.kahar2210@gmail.com", "Test mail-BookStore",
                    "To get Updated Book: click here->" +
                            "http://localhost:8087/book/getBook/"+getId);
            return newBook.get();
        }
        else{
            throw new UserException("Book not found");
        }
    }

    @Override
    public BookModel updateBookQuantity(int getBookId, int quantity) {
        Optional<BookModel> newBook = repo.findById(getBookId);
        if(newBook.isPresent()){
            newBook.get().setQuantity(quantity);
            repo.save(newBook.get());
            return newBook.get();
        }
        else{
            throw new UserException("Book not found");
        }
    }

    @Override
    public List<BookModel> getListOfBooksInSortedDsc() {
        List<BookModel> bookDetails=repo.findAll(Sort.by("bookName").descending());
        return bookDetails;
    }

    @Override
    public List<BookModel> getListOfBooksInSortedAce() {
        List<BookModel> bookDetails=repo.findAll(Sort.by("bookName").ascending());
        return bookDetails;
    }

    @Override
    public List<BookModel> getBookByName(String bookName) {
        return repo.findBookByName(bookName);
    }

}

