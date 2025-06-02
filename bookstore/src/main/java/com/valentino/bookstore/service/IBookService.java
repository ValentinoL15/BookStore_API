package com.valentino.bookstore.service;

import com.valentino.bookstore.dto.BookDTO;
import com.valentino.bookstore.dto.UpdateBookDTO;
import com.valentino.bookstore.model.Book;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IBookService {

    public List<Book> getBooks();

    public void createBook(Book book);

    public BookDTO getBook(Long id_book);

    public void deleteBook(Long id_book);

    public void editBook(Long id_book, UpdateBookDTO bookDto);


}
