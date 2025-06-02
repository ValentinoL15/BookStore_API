package com.valentino.bookstore.controller;

import com.valentino.bookstore.dto.BookDTO;
import com.valentino.bookstore.dto.UpdateBookDTO;
import com.valentino.bookstore.model.Book;
import com.valentino.bookstore.repository.IBookRepository;
import com.valentino.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private IBookRepository repoBook;

    @GetMapping("/book/{id_book}")
    public ResponseEntity<BookDTO> getBook(@PathVariable Long id_book) {

        try {

            BookDTO bookDto = bookService.getBook(id_book);
            return ResponseEntity.ok(bookDto);

        }catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

    }

    @PostMapping(value = "/crear-book", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> createBook(@RequestPart("book") @Validated Book book,
                                             @RequestPart("image")MultipartFile image) {

        try {
            // Guardar la imagen en una carpeta del servidor
            String fileName = UUID.randomUUID() + "_" + image.getOriginalFilename();
            Path imagePath = Paths.get("uploads/" + fileName);
            Files.createDirectories(imagePath.getParent()); // Crea la carpeta si no existe
            Files.write(imagePath, image.getBytes()); // ← puede lanzar IOException

            // Setear la URL de la imagen
            book.setImage("/uploads/" + fileName);

            // Guardar el libro
            bookService.createBook(book);

            return ResponseEntity.ok("Se ha creado el libro con éxito");

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al guardar la imagen: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear el libro");
        }

    }

    @DeleteMapping("/delete-book/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        try {

            bookService.deleteBook(id);
            return ResponseEntity.ok("El libro se ha eliminado con éxito");

        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @PutMapping("/update-book/{id_book}")
    public ResponseEntity<String> editBook(@PathVariable Long id_book,
                                           @RequestBody UpdateBookDTO bookDTO) {

        try {

            bookService.editBook(id_book,bookDTO);

            return ResponseEntity.ok("Libro editado con éxito");

        }
        catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

    }

}
