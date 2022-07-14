package com.bridgelabz.bookstoreproject.repository;


import com.bridgelabz.bookstoreproject.model.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBookDetailsRepository extends JpaRepository<BookModel,Integer> {
    // @Query(value = "SELECT * FROM book_model inner join book_name on book_model.book_id=book_name.book_id ",nativeQuery=true)

    @Query(value = "SELECT * FROM book_model WHERE book_name Like 'H%' ",nativeQuery = true)
    List<BookModel> findBookByName(String bookName);
}