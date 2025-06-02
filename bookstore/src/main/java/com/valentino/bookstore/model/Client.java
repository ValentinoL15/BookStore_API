package com.valentino.bookstore.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;
import java.util.UUID;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id_client;
    @NotBlank(message = "EL nombre es requerido")
    private String name;
    @NotBlank(message = "El apellido es requerido")
    private String lastName;
    @NotNull(message = "La edad es requerida")
    @Positive(message = "La edad no puede ser negativa")
    private Integer age;
    @NotBlank(message = "El pais de residencia es requerido")
    private String country;
    @ManyToMany
    @JoinTable(name = "client_books",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Book> books;

    public Client() {
    }

    public Client(UUID id_client, String name, String lastName, Integer age, String country, List<Book> books) {
        this.id_client = id_client;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.country = country;
        this.books = books;
    }

    public UUID getId_client() {
        return id_client;
    }

    public void setId_client(UUID id_client) {
        this.id_client = id_client;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
