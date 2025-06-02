package com.valentino.bookstore.repository;

import com.valentino.bookstore.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IClientRepository extends JpaRepository<Client, UUID> {
}
