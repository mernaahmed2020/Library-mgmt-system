package com.example.demo.controller;

import com.example.demo.model.entity.Book;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    // GET method to display all books
    @GetMapping
    public String getAllBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "bookList"; // Return the view name (HTML template)
    }

    // POST method to add a new book
    @PostMapping
    public String addBook(@RequestParam String title, @RequestParam String author, @RequestParam String genre) {
        Book newBook = new Book(title, author, genre);
        bookService.saveBook(newBook);
        return "redirect:/books"; // Redirect back to the books list page after adding the book
    }

    // DELETE method to delete a book by its ID
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        bookService.deleteBook(id);
        return "redirect:/books"; // Redirect back to the books list page after deleting the book
    }
}
