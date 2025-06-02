package com.valentino.bookstore.dto;

import com.valentino.bookstore.model.Book;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class BookDTO {

    private Long id_book;
    private String name;
    private String editorial;
    private String gender;
    private String image;
    private LocalDate published_at;
    private String author;
    private Integer price;
    private Integer quantity;

    public BookDTO(Long idBook, String name,Integer price , String editorial, String gender,  String image, String author, LocalDate publishedAt) {
        this.id_book = idBook;
        this.price = price;
        this.author = author;
        this.image = image;
        this.gender = gender;
        this.editorial = editorial;
        this.name = name;
        this.published_at = publishedAt;
    }

    public Long getId_book() {
        return id_book;
    }

    public void setId_book(Long id_book) {
        this.id_book = id_book;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public LocalDate getPublished_at() {
        return published_at;
    }

    public void setPublished_at(LocalDate published_at) {
        this.published_at = published_at;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
