package com.valentino.bookstore.repository;

import com.valentino.bookstore.model.BookStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBookStoreRepository extends JpaRepository<BookStore,Long> {
}
