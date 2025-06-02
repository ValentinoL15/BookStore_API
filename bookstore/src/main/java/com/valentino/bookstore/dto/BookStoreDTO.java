package com.valentino.bookstore.dto;

import com.valentino.bookstore.model.Book;

import java.util.List;

public class BookStoreDTO {

    private Long id;
    private List<BookDTO> books;

    public BookStoreDTO(Long id, List<BookDTO> books) {
        this.id = id;
        this.books = books;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<BookDTO> getBooks() {
        return books;
    }

    public void setBooks(List<BookDTO> books) {
        this.books = books;
    }
}
