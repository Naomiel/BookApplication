package com.example.bookapplication.serviceImpl;

import com.example.bookapplication.dto.request.BookRequest;
import com.example.bookapplication.dto.response.BookResponse;
import com.example.bookapplication.exception.BookNotFoundException;
import com.example.bookapplication.model.Book;
import com.example.bookapplication.repository.BookRepository;
import com.example.bookapplication.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public BookResponse createBook(BookRequest bookRequest) {
        Book book = mapRequestToBook(bookRequest);
        bookRepository.save(book);
        return mapBookToResponse(book);
    }
    @Override
    public List<BookResponse> getAllBooks() {
        List<Book> bookList = bookRepository.findAll();
        if (bookList.isEmpty()){
            return Collections.emptyList();
        }
        List<BookResponse> responseList = new ArrayList<>();
        for(Book book : bookList){
            BookResponse response = mapBookToResponse(book);
            responseList.add(response);
        }
        return  responseList;
//        return bookList.stream().map(book -> mapBookToResponse(book)).collect(Collectors.toList());
    }
    @Override
    public BookResponse getBookById(Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()){
            return mapBookToResponse(optionalBook.get());
        }
        throw new BookNotFoundException("Book not found for Id: "+ id);
    }

    @Override
    public BookResponse updateBook(Long id, BookRequest request) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()){
            Book book = optionalBook.get();
            if(Objects.nonNull(request.getYearPublished())){
                book.setYearPublished(request.getYearPublished());
            }
            if(Objects.nonNull(request.getAuthor())){
                book.setAuthor(request.getAuthor());
            }
            if(Objects.nonNull(request.getPrice())){
                book.setPrice(request.getPrice());
            }
            if(Objects.nonNull(request.getTitle())){
                book.setTitle(request.getTitle());
            }
            if(Objects.nonNull(request.getIsbn())){
                book.setIsbn(request.getIsbn());
            }
            bookRepository.save(book);
            return mapBookToResponse(book);
        }
        throw new BookNotFoundException("Book not found for Id: " + id);
    }

    @Override
    public String deleteBook(Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            bookRepository.deleteById(id);
            return "Book successfully deleted with Id: " + id;
        }
        throw new BookNotFoundException("Book not found for Id: " + id);
    }
    private BookResponse mapBookToResponse(Book book){
        return BookResponse.builder()
                .author(book.getAuthor())
                .createdAt(book.getCreatedAt())
                .price(book.getPrice())
                .title(book.getTitle())
                .isbn(book.getIsbn())
                .yearPublished(book.getYearPublished())
                .modifiedAt(book.getModifiedAt())
                .id(book.getId())
                .build();
    }
    private Book mapRequestToBook(BookRequest request){
        Book book = new Book();
        if(Objects.nonNull(request.getYearPublished())){
            book.setYearPublished(request.getYearPublished());
        }
        if(Objects.nonNull(request.getAuthor())){
            book.setAuthor(request.getAuthor());
        }
        if(Objects.nonNull(request.getPrice())){
            book.setPrice(request.getPrice());
        }
        if(Objects.nonNull(request.getTitle())){
            book.setTitle(request.getTitle());
        }
        if(Objects.nonNull(request.getIsbn())){
            book.setIsbn(request.getIsbn());
        }
        return book;
    }
}
