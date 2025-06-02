package com.valentino.bookstore.service;

import com.valentino.bookstore.dto.BookStoreDTO;
import com.valentino.bookstore.model.BookStore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IBookStoreService  {

    public List<BookStore> getAllBookstore();

    public BookStoreDTO getBookstore(Long id);

    public void createBookStore(BookStore bookStore);

    public void deleteBook(Long id_book);

}
