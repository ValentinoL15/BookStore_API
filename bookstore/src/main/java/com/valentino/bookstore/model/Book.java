package com.valentino.bookstore.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_book;
    @NotBlank(message = "Debes colocar el nombre del libro")
    private String name;
    @NotBlank(message = "Elige una editorial")
    private String editorial;
    @NotBlank(message = "Debes colocar un género literario")
    private String gender;
    private String image;
    @NotBlank(message = "Debes colocar un autor del libro")
    private String author;
    private int sales = 0;
    @NotNull(message = "Debes elegir una cantidad")
    @Positive(message = "La cantidad debe ser un número positivo")
    private Integer quantity;
    @NotNull(message = "Debes colocar un precio")
    @Positive(message = "El precio debe ser un número positivo")
    private Integer price;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(columnDefinition = "DATE")
    private LocalDate published_at;
    @ManyToOne
    @JoinColumn(name = "bookstore_id")
    private BookStore bookStore;
    @ManyToMany(mappedBy = "books")
    private List<Client> clients;

    public Book() {
    }

    public Book(Long id_book, String name, String editorial, String gender, String image, int sales, String author, Integer price, Integer quantity, LocalDate published_at, BookStore bookStore, List<Client> clients) {
        this.id_book = id_book;
        this.name = name;
        this.editorial = editorial;
        this.gender = gender;
        this.image = image;
        this.sales = sales;
        this.author = author;
        this.price = price;
        this.quantity = quantity;
        this.published_at = published_at;
        this.bookStore = bookStore;
        this.clients = clients;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public LocalDate getPublished_at() {
        return published_at;
    }

    public void setPublished_at(LocalDate published_at) {
        this.published_at = published_at;
    }

    public BookStore getBookStore() {
        return bookStore;
    }

    public void setBookStore(BookStore bookStore) {
        this.bookStore = bookStore;
    }
}
