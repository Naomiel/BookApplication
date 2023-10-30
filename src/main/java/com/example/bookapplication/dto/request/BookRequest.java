package com.example.bookapplication.dto.request;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class BookRequest {
    private String title;
    private String author;
    private Integer isbn;
    private Double price;
    private String yearPublished;
}
