package com.valentino.bookstore.dto;

import jakarta.persistence.Column;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class UpdateBookDTO {

    private Long id_book;
    private String name;
    private String editorial;
    private String gender;
    private String image;
    private String author;
    private int sales;
    private Integer quantity;
    private Integer price;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(columnDefinition = "DATE")
    private LocalDate published_at;


    public UpdateBookDTO() {
    }

    public UpdateBookDTO(Long id_book, String name, String editorial, String gender, String image, String author, int sales, Integer price, Integer quantity, LocalDate published_at) {
        this.id_book = id_book;
        this.name = name;
        this.editorial = editorial;
        this.gender = gender;
        this.image = image;
        this.author = author;
        this.sales = sales;
        this.price = price;
        this.quantity = quantity;
        this.published_at = published_at;
    }

    public Long getId_book() {
        return id_book;
    }

    public void setId_book(Long id_book) {
        this.id_book = id_book;
    }

    public LocalDate getPublished_at() {
        return published_at;
    }

    public void setPublished_at(LocalDate published_at) {
        this.published_at = published_at;
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

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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
}
