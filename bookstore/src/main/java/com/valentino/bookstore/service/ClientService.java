package com.valentino.bookstore.service;

import com.valentino.bookstore.dto.BookDTO;
import com.valentino.bookstore.model.Book;
import com.valentino.bookstore.model.Client;
import com.valentino.bookstore.dto.ClientDTOs.ClientDTO;
import com.valentino.bookstore.repository.IBookRepository;
import com.valentino.bookstore.repository.IClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.font.TextHitInfo;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ClientService implements IClientService{

    @Autowired
    private IClientRepository repoClient;

    @Autowired
    private IBookRepository repoBook;

    @Override
    public List<Client> getClients() {
        return List.of();
    }

    @Override
    public void createClient(Client client) {

        repoClient.save(client);

    }

    @Override
    public void deleteClient(UUID id_client) {

    }

    @Override
    public ClientDTO getClient(UUID id_client) {
        Client client = repoClient.findById(id_client).orElse(null);

        if (client == null) {
            throw new IllegalStateException("No existe el cliente");
        }

        List<BookDTO> bookDTOs = client.getBooks().stream().map(book -> new BookDTO(
                book.getId_book(),
                book.getName(),
                book.getPrice(),
                book.getEditorial(),
                book.getGender(),
                book.getImage(),
                book.getAuthor(),
                book.getPublished_at()
        )).collect(Collectors.toList());

        return new ClientDTO(
                client.getId_client(),
                client.getName(),
                client.getLastName(),
                client.getAge(),
                client.getCountry(),
                bookDTOs
        );

    }

    @Override
    public void buyBook(Long id_book, UUID id_client) {

        Book book = repoBook.findById(id_book).orElse(null);
        if(book == null) {
            throw new IllegalStateException("El libro no se encuentra");
        }

        Client client = repoClient.findById(id_client).orElse(null);

        List<BookDTO> bookDTOs = client.getBooks().stream().map(books -> new BookDTO(
                books.getId_book(),
                books.getName(),
                books.getPrice(),
                books.getEditorial(),
                books.getGender(),
                books.getImage(),
                books.getAuthor(),
                books.getPublished_at()
        )).collect(Collectors.toList());

        if(book.getQuantity() == 0) {
            throw new IllegalStateException("No hay stock del libro " + book.getName());
        }

        for(BookDTO libro : bookDTOs) {
            if(book.getId_book().equals(libro.getId_book())) {
                throw new IllegalStateException("El libro ya ha sido comprado por el usuario");
            }
        }

        client.getBooks().add(book);
        book.setSales(book.getSales() + 1);
        book.setQuantity(book.getQuantity() - 1);

        repoClient.save(client);

    }
}
