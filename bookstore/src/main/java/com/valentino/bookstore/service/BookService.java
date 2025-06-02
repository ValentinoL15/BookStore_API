package com.valentino.bookstore.service;

import com.valentino.bookstore.dto.BookDTO;
import com.valentino.bookstore.dto.UpdateBookDTO;
import com.valentino.bookstore.model.Book;
import com.valentino.bookstore.model.BookStore;
import com.valentino.bookstore.repository.IBookRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService implements IBookService{

    @Autowired
    private IBookRepository repoBooks;

    @Autowired
    private IBookStoreService repoBookstore;

    @Override
    public List<Book> getBooks() {
        List<Book> listBooks = repoBooks.findAll();

        return listBooks;
    }

    @Override
    public void createBook(Book book) {

            List<BookStore> bookStore = repoBookstore.getAllBookstore();
            if(bookStore.isEmpty()) {
                throw new IllegalStateException("No existe ninguna biblioteca, por favor crea una");
            }

            //Obtengo la biblioteca
            BookStore onlyBookStore = bookStore.get(0);

            //Seteo el book a la biblioteca
            book.setBookStore(onlyBookStore);

            //Creo el book
            repoBooks.save(book);

    }

    @Override
    public BookDTO getBook(Long id_book) {

        Book book = repoBooks.findById(id_book).orElse(null);

        BookDTO bookDTO = new BookDTO(
                book.getId_book(),
                book.getName(),
                book.getPrice(),
                book.getEditorial(),
                book.getGender(),
                book.getImage(),
                book.getAuthor(),
                book.getPublished_at()
        );

        return bookDTO;
    }

    @Override
    public void deleteBook(Long id_book) {

        Book book = repoBooks.findById(id_book).orElseThrow(() -> new RuntimeException("Libro no encontrado"));

        BookStore bookStore = book.getBookStore();

        if(bookStore != null) {
            bookStore.getBooks().remove(book);
            book.setBookStore(null);
        }

        repoBooks.deleteById(id_book);

    }

    @Override
    public void editBook(Long id_book, UpdateBookDTO bookDTO) {
        Book book = repoBooks.findById(id_book).orElse(null);

        if(bookDTO.getName() != null) book.setName(bookDTO.getName());
        if(bookDTO.getEditorial() != null) book.setEditorial(bookDTO.getEditorial());
        if(bookDTO.getGender() != null) book.setGender(bookDTO.getGender());
        if(bookDTO.getAuthor() != null) book.setAuthor(bookDTO.getAuthor());
        if(bookDTO.getQuantity() != null) book.setQuantity(bookDTO.getQuantity());
        if(bookDTO.getImage() != null) book.setImage(bookDTO.getImage());
        if(bookDTO.getPrice() != null) book.setPrice(bookDTO.getPrice());
        if(bookDTO.getPublished_at() != null) book.setPublished_at(bookDTO.getPublished_at());

        repoBooks.save(book);

    }
}
