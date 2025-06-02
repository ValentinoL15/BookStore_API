package com.valentino.bookstore.controller;

import com.valentino.bookstore.dto.BookStoreDTO;
import com.valentino.bookstore.model.BookStore;
import com.valentino.bookstore.service.BookStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookStoreController {

    @Autowired
    private BookStoreService bookStoreService;

    @PostMapping("/crear-bookstore")
    public ResponseEntity<String> createBookStore(@RequestBody BookStore bookStore) {
        try {

            bookStoreService.createBookStore(bookStore);
            return ResponseEntity.ok("La biblioteca se ha creado con éxito");

        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @GetMapping("/biblioteca/{id}")
    public ResponseEntity<BookStoreDTO> getBookstore(@PathVariable Long id) {
        try {

           BookStoreDTO dto = bookStoreService.getBookstore(id);
           return ResponseEntity.ok(dto);

        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }


    }

}
