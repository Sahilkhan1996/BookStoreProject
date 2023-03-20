package com.bridgelabz.bookstore.repository;

import com.bridgelabz.bookstore.model.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<BookModel, Integer> {

    @Query(value = "select * from book_model where book_Name LIKE %:bname%", nativeQuery = true)
    BookModel findBookByName(String bname);

//    @Query(value = "select * from book_model order by book_Name", nativeQuery = true)
//    List<BookModel> booksInAsc();
//
//    @Query(value = "select * from book_model order by book_Name desc", nativeQuery = true)
//    List<BookModel> booksInDesc();

}
