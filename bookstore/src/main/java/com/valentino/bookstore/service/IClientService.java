package com.valentino.bookstore.service;

import com.valentino.bookstore.dto.BookDTO;
import com.valentino.bookstore.model.Book;
import com.valentino.bookstore.model.Client;
import com.valentino.bookstore.dto.ClientDTOs.ClientDTO;

import java.util.List;
import java.util.UUID;

public interface IClientService {

    public List<Client> getClients();

    public void createClient(Client client);

    public void deleteClient(UUID id_client);

    public ClientDTO getClient(UUID id_client);

    public void buyBook(Long id_book, UUID id_client);

}
