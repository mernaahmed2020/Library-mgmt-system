package com.example.demo.service;

import com.example.demo.model.entity.Book;
import com.example.demo.model.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    // Get all books
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Save a new book
    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    // Delete a book by ID
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    // Search books by title, author, or genre
    public List<Book> searchBooks(String search) {
        return bookRepository.findByTitleContainingOrAuthorContainingOrGenreContaining(search, search, search);
    }
}
