package com.example.bookapplication.service;

import com.example.bookapplication.dto.request.BookRequest;
import com.example.bookapplication.dto.response.BookResponse;

import java.util.List;


public interface BookService {
    BookResponse createBook(BookRequest bookRequest);

    List<BookResponse> getAllBooks();

    BookResponse getBookById(Long id);

    BookResponse updateBook(Long id, BookRequest bookRequest);

    String deleteBook(Long id);
}
