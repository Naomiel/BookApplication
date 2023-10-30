package com.example.bookapplication.controller;

import com.example.bookapplication.dto.request.BookRequest;
import com.example.bookapplication.dto.response.BookResponse;
import com.example.bookapplication.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/create-book")
    public BookResponse createBook(@RequestBody BookRequest bookRequest) {
        return bookService.createBook(bookRequest);
    }
    @GetMapping("/get-all-book")
    public List<BookResponse> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/get-book/{id}")
    public BookResponse getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @PutMapping("/update-book/{id}")
    public BookResponse updateBook(@PathVariable Long id, @RequestBody BookRequest bookRequest) {
        return bookService.updateBook(id, bookRequest);
    }
    @DeleteMapping("/delete-book/{id}")
    public String deleteBook(@PathVariable Long id) {
       return bookService.deleteBook(id);
    }
}
