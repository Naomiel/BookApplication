package com.example.bookapplication.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
public class BookResponse {
    private Long id;
    private String title;
    private String author;
    private Integer isbn;
    private Double price;
    private String yearPublished;
    private Date createdAt;
    private Date modifiedAt;
}
