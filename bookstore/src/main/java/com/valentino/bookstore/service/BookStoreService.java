package com.valentino.bookstore.service;

import com.valentino.bookstore.dto.BookDTO;
import com.valentino.bookstore.dto.BookStoreDTO;
import com.valentino.bookstore.model.BookStore;
import com.valentino.bookstore.repository.IBookStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookStoreService implements IBookStoreService {

    @Autowired
    private IBookStoreRepository repoBookStore;

    @Override
    public List<BookStore> getAllBookstore() {
        List<BookStore> listBookstore = repoBookStore.findAll();
        return listBookstore;
    }

    @Override
    public BookStoreDTO getBookstore(Long id) {
        BookStore bookStore = repoBookStore.findById(id).orElse(null);

        List<BookDTO> bookDTOs = bookStore.getBooks().stream().map(book -> new BookDTO(
                book.getId_book(),
                book.getName(),
                book.getPrice(),
                book.getEditorial(),
                book.getGender(),
                book.getImage(),
                book.getAuthor(),
                book.getPublished_at()
        )).collect(Collectors.toList());

        return new BookStoreDTO(bookStore.getId(), bookDTOs);
    }

    @Override
    public void createBookStore(BookStore bookStore) {
        List<BookStore> existingBookstore = repoBookStore.findAll();
        if(!existingBookstore.isEmpty()) {
            throw new IllegalStateException("Ya existe una Biblioteca, no puedes crear otra");
        }
        repoBookStore.save(bookStore);
    }

    @Override
    public void deleteBook(Long id_book) {
        repoBookStore.deleteById(id_book);
    }
}
