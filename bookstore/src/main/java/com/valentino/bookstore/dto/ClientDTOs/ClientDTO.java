package com.valentino.bookstore.dto.ClientDTOs;

import com.valentino.bookstore.dto.BookDTO;
import com.valentino.bookstore.model.Book;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ClientDTO {

    private UUID id_client;
    private String name;
    private String lastName;
    private Integer age;
    private String country;
    private List<BookDTO> books;

    public ClientDTO(UUID id_client, String name,String lastName, Integer age, String country ,List<BookDTO> books) {
        this.id_client = id_client;
        this.books = books;
        this.country = country;
        this.age = age;
        this.lastName = lastName;
        this.name = name;
    }

    public List<BookDTO> getBooks() {
        return books;
    }

    public void setBooks(List<BookDTO> books) {
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

}
