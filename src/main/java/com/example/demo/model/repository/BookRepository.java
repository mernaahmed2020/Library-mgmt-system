package com.example.demo.model.repository;

import com.example.demo.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    // Search books by title, author, or genre
    List<Book> findByTitleContainingOrAuthorContainingOrGenreContaining(String title, String author, String genre);
}
